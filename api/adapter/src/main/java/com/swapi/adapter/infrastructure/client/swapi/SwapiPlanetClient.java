package com.swapi.adapter.infrastructure.client.swapi;

import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetPaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetWrapperDTO;

import java.util.List;

public interface SwapiPlanetClient {

    SwapiPlanetPaginatedResponseDTO query(int page, int size, String name);

    SwapiPlanetPaginatedResponseDTO getAllPaginated(int page, int size);

    List<SwapiPlanetWrapperDTO> searchByName(String name);
}
