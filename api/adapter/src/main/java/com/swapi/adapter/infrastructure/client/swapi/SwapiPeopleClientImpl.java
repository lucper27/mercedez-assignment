package com.swapi.adapter.infrastructure.client.swapi;

import com.swapi.adapter.application.query.sort.common.utils.PaginationCalculation;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPeoplePaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPersonWrapperDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiSearchPeopleResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class SwapiPeopleClientImpl implements SwapiPeopleClient {

    private final RestTemplate restTemplate;

    @Value("${swapi.base-url}")
    private String baseUrl;

    public SwapiPeopleClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public SwapiPeoplePaginatedResponseDTO query(int page, int size, String name) {
        boolean nameFilterPresent = name != null && !name.isEmpty() ;

        if (nameFilterPresent) {
                List<SwapiPersonWrapperDTO> filtered = searchByName(name);
                return buildPaginatedResponse(filtered, page, size);
        }

        return getAllPaginated(page, size);
    }

    @Override
    public SwapiPeoplePaginatedResponseDTO getAllPaginated(int page, int size) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/people")
                .queryParam("expanded", "true")
                .queryParam("page", page)
                .queryParam("limit", size)
                .toUriString();

        SwapiPeoplePaginatedResponseDTO response = restTemplate.getForObject(url, SwapiPeoplePaginatedResponseDTO.class);

        return response;
    }

    @Override
    public List<SwapiPersonWrapperDTO> searchByName(String name) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/people")
                .queryParam("expanded", "true")
                .queryParam("name", name)
                .toUriString();

        SwapiSearchPeopleResponseDTO response = restTemplate.getForObject(url, SwapiSearchPeopleResponseDTO.class);
        return response.getResult();
    }

    private SwapiPeoplePaginatedResponseDTO buildPaginatedResponse(List<SwapiPersonWrapperDTO> all, int page, int size) {
        SwapiPeoplePaginatedResponseDTO response = new SwapiPeoplePaginatedResponseDTO();

        int sizeLimit = size == 0 ? all.size() : size;

        int fromIndex = PaginationCalculation.calculateFromIndex(page, sizeLimit);
        int toIndex = PaginationCalculation.calculateToIndex(fromIndex, sizeLimit, all.size());
        int totalPages = PaginationCalculation.calculateTotalPages(all.size(), sizeLimit);

        List<SwapiPersonWrapperDTO> paginated = all.subList(fromIndex, toIndex);

        response.setTotal_records(all.size());
        response.setTotal_pages(totalPages);
        response.setResults(paginated);
        return response;
    }

}
