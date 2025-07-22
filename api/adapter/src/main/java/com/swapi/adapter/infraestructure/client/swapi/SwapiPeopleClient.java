package com.swapi.adapter.infraestructure.client.swapi;

import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPaginatedResponseDTO;
import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPersonWrapperDTO;

import java.util.List;

public interface SwapiPeopleClient {

    SwapiPaginatedResponseDTO query(int page, int size, String name);

    SwapiPaginatedResponseDTO getAllPaginated(int page, int size);

    List<SwapiPersonWrapperDTO> searchByName(String name);
}
