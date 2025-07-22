package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;

public interface PeopleNaturalQuery {

    PaginatedResponse<PeopleResponseDTO> execute(PeopleQueryParameters params);
}
