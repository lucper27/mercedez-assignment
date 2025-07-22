package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.mapper.SwapiPeopleResponseMapper;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.infraestructure.client.swapi.SwapiPeopleClient;
import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPaginatedResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleNaturalQueryImpl implements PeopleNaturalQuery {

    private final SwapiPeopleClient swapiPeopleClient;

    private final SwapiPeopleResponseMapper swapiPeopleResponseMapper;

    public PeopleNaturalQueryImpl(SwapiPeopleClient swapiPeopleClient, SwapiPeopleResponseMapper swapiPeopleResponseMapper) {
        this.swapiPeopleClient = swapiPeopleClient;
        this.swapiPeopleResponseMapper = swapiPeopleResponseMapper;
    }

    @Override
    public PaginatedResponse<PeopleResponseDTO> execute(PeopleQueryParameters params) {
        SwapiPaginatedResponseDTO response = swapiPeopleClient.query(params.getPage(), params.getSize(), params.getName());

        List<PeopleResponseDTO> peopleResponseDTOS = response.getResults()
                .stream()
                .map(swapiPeopleResponseMapper::toDto)
                .toList();

        PaginatedResponse<PeopleResponseDTO> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(peopleResponseDTOS);
        paginatedResponse.setTotalElements(response.getTotal_records());
        paginatedResponse.setTotalPages(response.getTotal_pages());
        paginatedResponse.setPage(params.getPage());
        paginatedResponse.setSize(params.getSize());

        return paginatedResponse;
    }
}
