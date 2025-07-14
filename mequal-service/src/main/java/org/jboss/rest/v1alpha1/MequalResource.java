package org.jboss.rest.v1alpha1;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.ext.web.FileUpload;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
//todo we're using strings currently
import java.security.MessageDigest;

import org.jboss.mequal.model.Manifest;
import org.jboss.mequal.model.ManifestRepository;

@Path("api/v1alpha1/manifest")
@Slf4j
@ApplicationScoped
public class MequalResource {

@Inject
ManifestRepository manifestRepository;
    
@GET
@Path("/{id}")
public Manifest getByID(Long id)
{
    return manifestRepository.findById(id);    
}

@GET
@Path("/{sha256}")
public Uni<Manifest> getBySha(String sha256)
{
    return manifestRepository.findBySha256(sha256);
}


/*
 * Todo, need to do pagination or else this would 
 * end up being too big to return
 */
/*
@GET
@Path("/all")
public Multi<Manifest> getAll(){
}
*/

@POST
@Path("/upload")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public Response upload(FileUpload manifest)
{
    log.debug(manifest.fileName());
    manifest.fileName();
    return null;

}

@GET
@Path("/upload")
public Response fetch(URI manifestlocation)
{
    return null;
}
}