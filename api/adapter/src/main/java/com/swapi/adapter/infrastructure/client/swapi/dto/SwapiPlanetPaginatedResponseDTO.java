package com.swapi.adapter.infrastructure.client.swapi.dto;

import java.util.List;

public class SwapiPlanetPaginatedResponseDTO extends SwapiBasePaginatedResponseDTO {

    private List<SwapiPlanetWrapperDTO> results;

    public SwapiPlanetPaginatedResponseDTO() {
        super();
    }


    public List<SwapiPlanetWrapperDTO> getResults() {
        return results;
    }

   
    public void setResults(List<SwapiPlanetWrapperDTO> results) {
        this.results = results;
    }
}
