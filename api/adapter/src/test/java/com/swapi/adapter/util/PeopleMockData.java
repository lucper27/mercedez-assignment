package com.swapi.adapter.util;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPeoplePaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPersonDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPersonWrapperDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiSearchPeopleResponseDTO;

import java.util.List;

public class PeopleMockData {

    public static PeopleResponseDTO lukeSkywalkerDto() {
        PeopleResponseDTO dto = new PeopleResponseDTO();
        dto.setName("Luke Skywalker");
        dto.setBirthYear("19BBY");
        dto.setEyeColor("blue");
        dto.setGender("male");
        dto.setHairColor("blond");
        dto.setHeight("172");
        dto.setMass("77");
        dto.setSkinColor("fair");
        dto.setHomeworld("https://www.swapi.tech/api/planets/1");
        dto.setCreated("2025-07-22T16:28:46.488Z");
        dto.setEdited("2025-07-22T16:28:46.488Z");
        dto.setUrl("https://www.swapi.tech/api/people/1");
        return dto;
    }

    public static SwapiPersonWrapperDTO lukeSkywalkerWrapper() {
        SwapiPersonWrapperDTO wrapper = new SwapiPersonWrapperDTO();
        SwapiPersonDTO dto = new SwapiPersonDTO();
        dto.setName("Luke Skywalker");
        dto.setBirth_year("19BBY");
        dto.setEye_color("blue");
        dto.setGender("male");
        dto.setHair_color("blond");
        dto.setHeight("172");
        dto.setMass("77");
        dto.setSkin_color("fair");
        dto.setHomeworld("https://www.swapi.tech/api/planets/1");
        dto.setCreated("2025-07-22T16:28:46.488Z");
        dto.setEdited("2025-07-22T16:28:46.488Z");
        dto.setUrl("https://www.swapi.tech/api/people/1");
        wrapper.setProperties(dto);
        return wrapper;
    }

    public static SwapiPeoplePaginatedResponseDTO swapiPeopleResponse() {
        SwapiPeoplePaginatedResponseDTO response = new SwapiPeoplePaginatedResponseDTO();
        response.setTotal_records(1);
        response.setTotal_pages(1);
        response.setResults(List.of(lukeSkywalkerWrapper()));
        return response;
    }

    public static SwapiSearchPeopleResponseDTO swapiSearchResponse() {
        SwapiSearchPeopleResponseDTO response = new SwapiSearchPeopleResponseDTO();
        response.setResult(List.of(lukeSkywalkerWrapper()));
        return response;
    }

    public static PaginatedResponse<PeopleResponseDTO> paginatedPeopleResponse() {
        PaginatedResponse<PeopleResponseDTO> response = new PaginatedResponse<>();
        response.setContent(List.of(lukeSkywalkerDto()));
        response.setPage(1);
        response.setSize(1);
        response.setTotalElements(1);
        response.setTotalPages(1);
        return response;
    }
}
