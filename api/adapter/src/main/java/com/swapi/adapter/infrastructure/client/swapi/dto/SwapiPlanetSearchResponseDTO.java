package com.swapi.adapter.infrastructure.client.swapi.dto;

import java.util.List;

public class SwapiPlanetSearchResponseDTO extends SwapiSearchResponseDTO {

    List<SwapiPlanetWrapperDTO> result;

    public SwapiPlanetSearchResponseDTO() {
        super();
    }

    public List<SwapiPlanetWrapperDTO> getResult() {
        return result;
    }

    public void setResult(List<SwapiPlanetWrapperDTO> result) {
        this.result = result;
    }
}
