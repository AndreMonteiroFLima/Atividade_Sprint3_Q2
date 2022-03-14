package br.com.compass.pb.exceptions;


import br.com.compass.pb.model.ErroMessage;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExcessãoGenerica implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        ErroMessage mensagem = new ErroMessage("Ainda não sabemos o que deu errado, mas quando descobrirmos contamos para você: "+e,500,"http://localhost:8080/ProjetoIndividualWeb2/");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
