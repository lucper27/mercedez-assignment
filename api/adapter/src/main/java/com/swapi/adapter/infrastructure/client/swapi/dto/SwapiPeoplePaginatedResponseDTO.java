package com.swapi.adapter.infrastructure.client.swapi.dto;

import java.util.List;

public class SwapiPeoplePaginatedResponseDTO extends SwapiBasePaginatedResponseDTO {

    private List<SwapiPersonWrapperDTO> results;

    public SwapiPeoplePaginatedResponseDTO() {
        super();
    }


    public List<SwapiPersonWrapperDTO> getResults() {
        return results;
    }


    public void setResults(List<SwapiPersonWrapperDTO> results) {
        this.results = results;
    }
}
