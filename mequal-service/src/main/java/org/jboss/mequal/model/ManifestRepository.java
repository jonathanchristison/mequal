package org.jboss.mequal.model;

//todo see below
import java.security.MessageDigest;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ManifestRepository implements PanacheRepositoryBase<Manifest, Long> {

    /* todo We should instead use MessageDigest in future */
    public Uni<Manifest> findBySha256(String sha256)
    {
        return find("sha256", sha256).firstResult();
    }
    
}
