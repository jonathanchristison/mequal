package org.jboss.mequal.model;

import java.net.URI;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Table(name = "manifest")
@Entity
public class Manifest extends PanacheEntityBase {

    /*
     * Good old fashioned Long as id
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id; 

    /*
     * When we got the request
     */
    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private Instant created;


    /*
     * The main identifier of the manifest
     */
    @Column(name = "sha256", nullable = false, unique = true)
    private MessageDigest sha256; 

    /**
     * The URI location 
     * 
     * The URI for manifests which are to be fetched
     * 
     * Note:
     * The _local_ location is a predictable pattern based on SHA256
     */
    @Column(name = "location", nullable = false)
    private URI location;

    /*
     * Do we have the complete manifest file?
     * (fetched or uploaded)
     */
    @Column(name = "fetched", nullable = false)
    private Boolean fetched;

    /*
     * The spec i.e CycloneDX 1.6
     * This doesn't mean we plan to verify the specification
     * but we may use it to reprocess existing manifest 
     */
    @Column(name = "specification", nullable = true)
    private String specification;  

    /* 
     * The result for the evaluation
     * We could choose OneToOne here but the possibility
     * of regenerating with different policies means we may
     * wish to keep multiple results
    */
    @OneToMany
    private List<MequalResult> result;


    /*
     * Metadata type fields 
     * /
    /*
     * Original filename at upload or download time
     */
    @Column(name = "filename")
    private String filename;

    /*
     * Filesize uncompressed
     */
    @Column(name = "originalFileSizeBytes")
    private long filesizeBytes;

    /*
     * Filesize compressed
     */
    @Column(name = "compressedFileSizeBytes")
    private long compressedFileSize;

    /*
     * What was used to compress it
     */
    @Column(name = "compression")
    private String compression;
}
