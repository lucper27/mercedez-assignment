package com.swapi.adapter.application.query.sort.planet;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.common.QueryParameters;

public interface PlanetNaturalQuery {

    PaginatedResponse<PlanetResponseDTO> execute(QueryParameters params);
}
