package com.swapi.adapter.application.service;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.domain.service.PeopleService;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Override
    public PaginatedResponse<PeopleResponseDTO> query() {
        return null;
    }

}
