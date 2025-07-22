package com.swapi.adapter.domain.service;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.people.PeopleQueryParameters;


public interface PeopleService {

    PaginatedResponse<PeopleResponseDTO> query(PeopleQueryParameters params);
}
