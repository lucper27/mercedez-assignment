package com.swapi.adapter.infraestructure.client.swapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPaginatedResponseDTO {

    private String message;

    private int total_records;

    private int total_pages;

    private String previous;

    private String next;

    private List<SwapiPersonWrapperDTO> results;

    public SwapiPaginatedResponseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal_records() {
        return total_records;
    }

    public void setTotal_records(int total_records) {
        this.total_records = total_records;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<SwapiPersonWrapperDTO> getResults() {
        return results;
    }

    public void setResults(List<SwapiPersonWrapperDTO> results) {
        this.results = results;
    }
}
