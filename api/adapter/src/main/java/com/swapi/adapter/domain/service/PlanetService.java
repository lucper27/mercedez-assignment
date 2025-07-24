package com.swapi.adapter.domain.service;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.common.QueryParameters;

public interface PlanetService {

    PaginatedResponse<PlanetResponseDTO> query(QueryParameters params);
}
