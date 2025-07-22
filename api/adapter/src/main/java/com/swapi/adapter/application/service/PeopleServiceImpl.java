package com.swapi.adapter.application.service;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.people.PeopleNaturalQuery;
import com.swapi.adapter.application.query.sort.people.PeopleQueryParameters;
import com.swapi.adapter.application.query.sort.people.PeopleSortedQuery;
import com.swapi.adapter.domain.service.PeopleService;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final PeopleNaturalQuery naturalQuery;

    private final PeopleSortedQuery sortedQuery;

    private final int MINIMUM = 1;

    public PeopleServiceImpl(PeopleNaturalQuery naturalQuery, PeopleSortedQuery sortedQuery) {
        this.naturalQuery = naturalQuery;
        this.sortedQuery = sortedQuery;
    }

    @Override
    public PaginatedResponse<PeopleResponseDTO> query(PeopleQueryParameters params) {
        boolean sort = params.getSort() != null && !params.getSort().isEmpty();
        if (sort) {
            return sortedQuery.execute(params);
        }

        return naturalQuery.execute(params);
    }

}
