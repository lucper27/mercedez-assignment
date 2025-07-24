package com.swapi.adapter.infrastructure.client.swapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPlanetWrapperDTO extends SwapiItemWrapperDTO {

    private SwapiPlanetDTO properties;

    public SwapiPlanetWrapperDTO() {
        super();
    }

    public SwapiPlanetDTO getProperties() {
        return properties;
    }

    public void setProperties(SwapiPlanetDTO properties) {
        this.properties = properties;
    }
}
