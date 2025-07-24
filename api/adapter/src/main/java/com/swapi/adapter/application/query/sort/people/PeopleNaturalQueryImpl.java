package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.mapper.SwapiPeopleResponseMapper;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.common.QueryParameters;
import com.swapi.adapter.infrastructure.client.swapi.SwapiPeopleClient;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPeoplePaginatedResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleNaturalQueryImpl implements PeopleNaturalQuery {

    private final Logger log = LoggerFactory.getLogger(PeopleNaturalQueryImpl.class);

    private final SwapiPeopleClient swapiPeopleClient;

    private final SwapiPeopleResponseMapper swapiPeopleResponseMapper;

    public PeopleNaturalQueryImpl(SwapiPeopleClient swapiPeopleClient, SwapiPeopleResponseMapper swapiPeopleResponseMapper) {
        this.swapiPeopleClient = swapiPeopleClient;
        this.swapiPeopleResponseMapper = swapiPeopleResponseMapper;
    }

    @Override
    public PaginatedResponse<PeopleResponseDTO> execute(QueryParameters params) {
        log.debug("executing people natural query with params {}", params);
        SwapiPeoplePaginatedResponseDTO response = swapiPeopleClient.query(params.getPage(), params.getSize(), params.getName());

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
