package br.com.compass.pb.exceptions;

import br.com.compass.pb.model.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LimiteDeCidadesExceptionMapper implements ExceptionMapper<LimiteDeCidadesException> {

    @Override
    public Response toResponse(LimiteDeCidadesException e) {
        ErroMessage mensagem = new ErroMessage("Limite de cidades diario alcançado! Favor tentar amanhã",Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),"http://localhost:8080/WebPrevisaoDoTempo");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
    }
}
