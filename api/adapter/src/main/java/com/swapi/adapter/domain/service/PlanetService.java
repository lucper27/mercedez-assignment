package com.swapi.adapter.domain.service;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;

public interface PlanetService {

    PaginatedResponse<PlanetResponseDTO> query();
}
