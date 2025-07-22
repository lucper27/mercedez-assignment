package com.swapi.adapter.application.service;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.domain.service.PlanetService;
import org.springframework.stereotype.Service;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Override
    public PaginatedResponse<PlanetResponseDTO> query() {
        return null;
    }

}
