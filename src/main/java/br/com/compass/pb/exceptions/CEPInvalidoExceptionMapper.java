package br.com.compass.pb.exceptions;

import br.com.compass.pb.model.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CEPInvalidoExceptionMapper implements ExceptionMapper<CEPInvalidoException> {

    @Override
    public Response toResponse(CEPInvalidoException e) {
        ErroMessage mensagem = new ErroMessage(""+e,Response.Status.BAD_REQUEST.getStatusCode(),"http://localhost:8080/WebPrevisaoDoTempo");
        return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
    }
}
