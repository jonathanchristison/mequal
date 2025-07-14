package org.jboss.mequal.opa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OPAQueryPayload {

    @JsonProperty("query")
    private final String query;

    @JsonProperty("input")
    private final String input;
    
}
