package org.jboss.mequal.opa;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.mequal.opa.dto.OPAQueryPayload;
import org.jboss.mequal.opa.dto.OPAQueryResponse;
import org.jboss.mequal.errors.ClientException;
import org.jboss.mequal.errors.ForbiddenException;
import org.jboss.mequal.errors.UnauthorizedException;
import org.jboss.mequal.errors.NotFoundException;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import jakarta.ws.rs.Produces;


@ApplicationScoped
@ClientHeaderParam(name = "User-Agent", value = "mequal")
@RegisterRestClient(configKey = "mequal")
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OPAClient {
    
    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    OPAQueryResponse getQuery(@QueryParam("pretty") boolean pretty, OPAQueryPayload qr);

    @ClientExceptionMapper
    @Blocking
    static RuntimeException toException(Response response) {
        String message = response.readEntity(String.class);

        return switch (response.getStatus()) {
            case 400 -> new ClientException("Bad request", List.of(message));
            case 401 ->
                new UnauthorizedException("Caller is unauthorized to access resource; {}", message, List.of(message));
            case 403 -> new ForbiddenException("Caller is forbidden to access resource; {}", message, List.of(message));
            case 404 -> new NotFoundException("Requested resource was not found; {}", message, List.of(message));
            default -> null;
        };

    }
    
}
