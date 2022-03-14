package br.com.compass.pb.resources;


import br.com.compass.pb.model.Previsao;
import br.com.compass.pb.service.PrevisaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

@Path("previsao")
public class PrevisaoTempoResource {

    private PrevisaoService previsaoSerivce = new PrevisaoService();

    @Path("/")
    @POST
    public void retornaPrevisao(@FormParam("cep") String CEP,
                                    @Context HttpServletRequest httpServletRequest,
                                    @Context HttpServletResponse httpServletResponse){
        Previsao previsao=previsaoSerivce.retornaPrevisaoService(CEP);
        previsaoSerivce.redirecionaTelaPrevisao(previsao,httpServletRequest,httpServletResponse);
    }

    @Path("/json/{CEP}")
    @POST
    public Response retornaPrevisaoJson(@PathParam("CEP") String CEP,
                                @Context HttpServletRequest httpServletRequest,
                                @Context HttpServletResponse httpServletResponse){
        Previsao previsao=previsaoSerivce.retornaPrevisaoService(CEP);
        return Response.ok().entity(previsao.toJson()).build();
    }

}
