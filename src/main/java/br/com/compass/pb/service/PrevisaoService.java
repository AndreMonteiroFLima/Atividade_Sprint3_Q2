package br.com.compass.pb.service;

import br.com.compass.pb.dao.PrevisaoDAO;
import br.com.compass.pb.dao.PrevisoesFuturasDAO;
import br.com.compass.pb.exceptions.CEPInvalidoException;
import br.com.compass.pb.exceptions.LimiteDeCidadesException;
import br.com.compass.pb.model.Endereco;
import br.com.compass.pb.model.Previsao;
import br.com.compass.pb.util.ApiCepUtil;
import br.com.compass.pb.util.CepValidatorUtil;
import br.com.compass.pb.util.HGBrasilUtil;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PrevisaoService {
    PrevisaoDAO previsaoDAO;
    PrevisoesFuturasDAO previsoesFuturasDAO;

    public Previsao retornaPrevisaoService(String CEP){
        CEP=CEP.replace('-',' ');
        if(CepValidatorUtil.isValid(CEP)){
            String enderecoJson = ApiCepUtil.pegaEnderecoPeloCep(CEP);
            Endereco endereco = (Endereco) new Gson().fromJson(enderecoJson,Endereco.class);
            String lugadouro =endereco.getCity()+","+endereco.getState();

            previsaoDAO= new PrevisaoDAO();
            Previsao previsao=previsaoDAO.verificaSeExisteRequest(endereco.getCity());
            if(previsao==null) {
                Long i = previsaoDAO.contaRequests();
                if(i<10) {
                    String previsaoJson = HGBrasilUtil.previsaoUtilHg(lugadouro);
                    previsao = (Previsao) new Gson().fromJson(previsaoJson, Previsao.class);
                    previsao.getForecast().stream().forEach(p->{
                        previsoesFuturasDAO= new PrevisoesFuturasDAO();
                        previsoesFuturasDAO.cadastrar(p);
                        previsoesFuturasDAO.commit();
                    });
                    previsaoDAO.cadastrar(previsao);
                    previsaoDAO.commit();
                    previsaoDAO.close();
                }else
                    throw new LimiteDeCidadesException();
            }
            return previsao;

        }
        else
            throw new CEPInvalidoException("CEP Invalido tente Novamente");

    }

    public void redirecionaTelaPrevisao(Previsao previsao,HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        httpRequest.setAttribute("previsao",previsao);
        RequestDispatcher rd = httpRequest.getRequestDispatcher("/Previsao.jsp");
        try {
            rd.forward(httpRequest,httpResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


}
