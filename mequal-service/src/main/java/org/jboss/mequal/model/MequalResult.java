package org.jboss.mequal.model;

import java.net.URI;
import java.security.MessageDigest;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MequalResult extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id; 

    /*
     * The sha256 of the result
     * we can use this to deduplicate
     */
    @Column(name = "resultSHA256", nullable = false)
    private MessageDigest resultSha256;    
    
    /*
     * The location of the results json file
     */
    @Column(name = "location")
    private URI location; 
}
