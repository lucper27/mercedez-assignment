package com.swapi.adapter.application.service;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.mapper.SwapiPeopleResponseMapper;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.domain.service.PeopleService;
import com.swapi.adapter.infraestructure.client.swapi.SwapiPeopleClient;
import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPaginatedResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final SwapiPeopleClient swapiPeopleClient;

    private final SwapiPeopleResponseMapper swapiPeopleResponseMapper;

    public PeopleServiceImpl(SwapiPeopleClient swapiPeopleClient, SwapiPeopleResponseMapper swapiPeopleResponseMapper) {
        this.swapiPeopleClient = swapiPeopleClient;
        this.swapiPeopleResponseMapper = swapiPeopleResponseMapper;
    }

    @Override
    public PaginatedResponse<PeopleResponseDTO> query(int page, int size, String name) {
        SwapiPaginatedResponseDTO response = swapiPeopleClient.query(page, size, name);
        List<PeopleResponseDTO> peopleResponseDTOS = new ArrayList<>();

        peopleResponseDTOS = response.getResults()
                .stream()
                .map(swapiPeopleResponseMapper::toDto)
                .collect(Collectors.toUnmodifiableList());

        //hacerme un mapper
        PaginatedResponse<PeopleResponseDTO> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(peopleResponseDTOS);
        paginatedResponse.setTotalElements(response.getTotal_records());
        paginatedResponse.setTotalPages(response.getTotal_pages());

        return paginatedResponse;
    }

}
