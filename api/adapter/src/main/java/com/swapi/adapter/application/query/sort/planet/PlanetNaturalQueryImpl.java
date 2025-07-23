package com.swapi.adapter.application.query.sort.planet;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.mapper.SwapiPlanetResponseMapper;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.common.QueryParameters;
import com.swapi.adapter.infraestructure.client.swapi.SwapiPlanetClient;
import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPlanetPaginatedResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetNaturalQueryImpl implements PlanetNaturalQuery {

    private final SwapiPlanetClient swapiPlanetClient;

    private final SwapiPlanetResponseMapper swapiPlanetResponseMapper;

    public PlanetNaturalQueryImpl(SwapiPlanetClient swapiPlanetClient, SwapiPlanetResponseMapper swapiPlanetResponseMapper) {
        this.swapiPlanetClient = swapiPlanetClient;
        this.swapiPlanetResponseMapper = swapiPlanetResponseMapper;
    }

    @Override
    public PaginatedResponse<PlanetResponseDTO> execute(QueryParameters params) {
        SwapiPlanetPaginatedResponseDTO response = swapiPlanetClient.query(params.getPage(), params.getSize(), params.getName());

        List<PlanetResponseDTO> planetResponseDTOS = response.getResults()
                .stream()
                .map(swapiPlanetResponseMapper::toDto)
                .toList();

        PaginatedResponse<PlanetResponseDTO> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(planetResponseDTOS);
        paginatedResponse.setTotalElements(response.getTotal_records());
        paginatedResponse.setTotalPages(response.getTotal_pages());
        paginatedResponse.setPage(params.getPage());
        paginatedResponse.setSize(params.getSize());

        return paginatedResponse;
    }
}
