package com.swapi.adapter.application.service;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.common.QueryParameters;
import com.swapi.adapter.application.query.sort.planet.PlanetNaturalQuery;
import com.swapi.adapter.application.query.sort.planet.PlanetSortedQuery;
import com.swapi.adapter.domain.service.PlanetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlanetServiceImpl implements PlanetService {

    private final Logger log = LoggerFactory.getLogger(PlanetServiceImpl.class);

    private final PlanetNaturalQuery naturalQuery;

    private final PlanetSortedQuery sortedQuery;

    public PlanetServiceImpl(PlanetNaturalQuery planetNaturalQuery, PlanetSortedQuery planetSortedQuery) {
        this.naturalQuery = planetNaturalQuery;
        this.sortedQuery = planetSortedQuery;
    }

    @Override
    public PaginatedResponse<PlanetResponseDTO> query(QueryParameters params) {
        log.debug("query planets with params {}", params);
        boolean sort = params.getSort() != null && !params.getSort().isEmpty();
        if (sort) {
            return sortedQuery.execute(params);
        }

        return naturalQuery.execute(params);
    }

}
