package com.swapi.adapter.domain.service;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;


public interface PeopleService {

    PaginatedResponse<PeopleResponseDTO> query();
}
