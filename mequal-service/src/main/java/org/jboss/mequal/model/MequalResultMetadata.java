package org.jboss.mequal.model;

import java.net.URI;
import java.time.Instant;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "resultMetadata")
@Entity
public class MequalResultMetadata extends PanacheEntity {

    public enum MequalResultStatus {
        COMPLETE, FAILED, IN_PROGRESS
    } 
    /* 
    * The output schema URI, 
    * ie. how to verify the formatting
    * this will help with policy revisions
    */
    @Column(name = "schema")
    private URI schmea;

    /*
     * When the evaluation was started 
     * (when the request was sent to OPA)
     */
    @Column(name = "start", nullable = false, updatable = false)
    private Instant start;

    /*
     * When we got a result back 
     */
    @Column(name = "end", nullable = true)
    private Instant end; 

    /*
     * Evaluation status
     *  COMPLETE, FAILED, IN_PROGRESS
     */
    @Column(name = "status")
    private MequalResultStatus status;

}
