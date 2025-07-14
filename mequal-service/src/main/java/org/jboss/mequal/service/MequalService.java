package org.jboss.mequal.service;

import java.net.URI;
import java.nio.file.Path;

import org.jboss.mequal.model.Manifest;
import org.jboss.mequal.model.ManifestRepository;

import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.client.WebClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.mequal.service.utils.ManifestFileUtil;

@ApplicationScoped
public class MequalService {

    @Inject
    ManifestRepository manifestRepository;
    
    @Inject
    WebClient webClient;
    
    public Uni<Path> fetchRemoteManifest(URI uri)
    {
        return null; 
    }
}
