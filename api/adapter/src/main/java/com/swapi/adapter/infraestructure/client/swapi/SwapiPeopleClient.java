package com.swapi.adapter.infraestructure.client.swapi;

import com.swapi.adapter.domain.entity.People;
import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPaginatedResponseDTO;

public interface SwapiPeopleClient {

    SwapiPaginatedResponseDTO query(int page, int size, String name);
}
