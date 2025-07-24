package com.swapi.adapter.application.service;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.people.PeopleNaturalQuery;
import com.swapi.adapter.application.query.sort.common.QueryParameters;
import com.swapi.adapter.application.query.sort.people.PeopleSortedQuery;
import com.swapi.adapter.domain.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final Logger log = LoggerFactory.getLogger(PeopleServiceImpl.class);

    private final PeopleNaturalQuery naturalQuery;

    private final PeopleSortedQuery sortedQuery;

    public PeopleServiceImpl(PeopleNaturalQuery naturalQuery, PeopleSortedQuery sortedQuery) {
        this.naturalQuery = naturalQuery;
        this.sortedQuery = sortedQuery;
    }

    @Override
    public PaginatedResponse<PeopleResponseDTO> query(QueryParameters params) {
        log.debug("query people with params {}", params);
        boolean sort = params.getSort() != null && !params.getSort().isEmpty();
        if (sort) {
            return sortedQuery.execute(params);
        }

        return naturalQuery.execute(params);
    }

}
