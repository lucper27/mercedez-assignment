package com.swapi.adapter.infrastructure.client.swapi;

import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPeoplePaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPersonWrapperDTO;

import java.util.List;

public interface SwapiPeopleClient {

    SwapiPeoplePaginatedResponseDTO query(int page, int size, String name);

    SwapiPeoplePaginatedResponseDTO getAllPaginated(int page, int size);

    List<SwapiPersonWrapperDTO> searchByName(String name);
}
