package com.swapi.adapter.infrastructure.rest;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.common.QueryParameters;
import com.swapi.adapter.domain.service.PlanetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planets")
public class PlanetResource {

    private final Logger log = LoggerFactory.getLogger(PlanetResource.class);

    private final PlanetService planetService;

    public PlanetResource(PlanetService planetService) {
        this.planetService = planetService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<PaginatedResponse<PlanetResponseDTO>> queryPlanets(QueryParameters params) {
        log.debug("Rest request to query planets w params {}", params);
        PaginatedResponse<PlanetResponseDTO> response = planetService.query(params);
        return ResponseEntity.ok(response);
    }
}
