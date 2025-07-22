package com.swapi.adapter.infraestructure.client.swapi;

import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPaginatedResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class SwapiPeopleClientImpl implements SwapiPeopleClient {

    private final RestTemplate restTemplate;

    public SwapiPeopleClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public SwapiPaginatedResponseDTO query(int page, int size, String name) {

        String url = UriComponentsBuilder
                .fromHttpUrl("https://www.swapi.tech/api/people")
                .queryParam("expanded", "true")
                .queryParam("page", page)
                .queryParam("limit", size)
                .queryParam("name", name)
                .toUriString();

        SwapiPaginatedResponseDTO response = restTemplate.getForObject(url, SwapiPaginatedResponseDTO.class);

        return response;
    }

}
