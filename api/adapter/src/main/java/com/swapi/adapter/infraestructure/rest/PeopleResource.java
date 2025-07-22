package com.swapi.adapter.infraestructure.rest;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.people.PeopleQueryParameters;
import com.swapi.adapter.domain.service.PeopleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
public class PeopleResource {

    private final PeopleService peopleService;

    public PeopleResource(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<PeopleResponseDTO>> queryPeople(
            PeopleQueryParameters params
    ) {
        PaginatedResponse<PeopleResponseDTO> response = peopleService.query(params);
        return ResponseEntity.ok(response);
    }
}
