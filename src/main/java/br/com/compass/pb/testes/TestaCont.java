package br.com.compass.pb.testes;

import br.com.compass.pb.dao.PrevisaoDAO;
import br.com.compass.pb.dao.PrevisoesFuturasDAO;
import br.com.compass.pb.model.Previsao;
import br.com.compass.pb.model.PrevisoesFuturas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestaCont {
    public static void main(String[] args) {
        PrevisaoDAO previsaoDAO = new PrevisaoDAO();
        List<PrevisoesFuturas> previsoes = new ArrayList<>();
        PrevisoesFuturas previsoesFuturas = new PrevisoesFuturas();
        previsoesFuturas.setDate("11/03/2022");
        previsoesFuturas.setDescription("Nubaldo");
        previsoesFuturas.setMax(32);
        previsoesFuturas.setMin(25);
        previsoes.add(previsoesFuturas);

        PrevisoesFuturasDAO previsoesFuturasDAO= new PrevisoesFuturasDAO();
        previsoes.stream().forEach(p->{
            previsoesFuturasDAO.cadastrar(p);
            previsoesFuturasDAO.commit();
        });

        LocalDate date = LocalDate.parse("11/03/2022");
        Previsao previsao = new Previsao(0,9,date,"11:48","Nublado","vitória",previsoes);
        previsaoDAO.cadastrar(previsao);
        previsaoDAO.commit();
        Long i = previsaoDAO.contaRequests();
        previsaoDAO.close();
        System.out.println(i);
    }
}
