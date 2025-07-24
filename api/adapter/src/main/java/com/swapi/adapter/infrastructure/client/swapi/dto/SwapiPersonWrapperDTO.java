package com.swapi.adapter.infrastructure.client.swapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPersonWrapperDTO extends SwapiItemWrapperDTO {

    private SwapiPersonDTO properties;

    public SwapiPersonWrapperDTO() {
        super();
    }

    public SwapiPersonDTO getProperties() {
        return properties;
    }

    public void setProperties(SwapiPersonDTO properties) {
        this.properties = properties;
    }
}
