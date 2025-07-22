package com.swapi.adapter.infraestructure.client.swapi.dto;

import java.util.List;

public class SwapiSearchResponseDTO {

    private String message;

    private List<SwapiPersonWrapperDTO> result;

    public SwapiSearchResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SwapiPersonWrapperDTO> getResult() {
        return result;
    }

    public void setResult(List<SwapiPersonWrapperDTO> result) {
        this.result = result;
    }
}

