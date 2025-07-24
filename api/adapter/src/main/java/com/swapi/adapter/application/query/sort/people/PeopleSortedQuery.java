package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.common.QueryParameters;

public interface PeopleSortedQuery {

    PaginatedResponse<PeopleResponseDTO> execute(QueryParameters params);
}
