package com.swapi.adapter.infrastructure.client.swapi.dto;

import java.util.List;

public class SwapiSearchPeopleResponseDTO extends SwapiSearchResponseDTO{

    private List<SwapiPersonWrapperDTO> result;

    public SwapiSearchPeopleResponseDTO() {
        super();
    }

    public List<SwapiPersonWrapperDTO> getResult() {
        return result;
    }

    public void setResult(List<SwapiPersonWrapperDTO> result) {
        this.result = result;
    }
}
