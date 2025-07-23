package com.swapi.adapter.infraestructure.client.swapi;

import com.swapi.adapter.application.query.sort.common.utils.PaginationCalculation;
import com.swapi.adapter.infraestructure.client.swapi.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class SwapiPlanetClientImpl implements SwapiPlanetClient {

    private final RestTemplate restTemplate;

    @Value("${swapi.base-url}")
    private String baseUrl;


    public SwapiPlanetClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SwapiPlanetPaginatedResponseDTO query (int page, int size, String name) {
        boolean nameFilterPresent = name != null && !name.isEmpty() ;
        if (nameFilterPresent) {
            List<SwapiPlanetWrapperDTO> filtered = searchByName(name);
            return buildPaginatedResponse(filtered, page, size);
        }

        return getAllPaginated(page, size);
    }

    @Override
    public SwapiPlanetPaginatedResponseDTO getAllPaginated (int page, int size) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/planets")
                .queryParam("expanded", "true")
                .queryParam("page", page)
                .queryParam("limit", size)
                .toUriString();

        SwapiPlanetPaginatedResponseDTO response = restTemplate.getForObject(url, SwapiPlanetPaginatedResponseDTO.class);

        return response;
    }

    @Override
    public List<SwapiPlanetWrapperDTO> searchByName (String name) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/planets")
                .queryParam("expanded", "true")
                .queryParam("name", name)
                .toUriString();

        SwapiPlanetSearchResponseDTO response = restTemplate.getForObject(url, SwapiPlanetSearchResponseDTO.class);
        return response.getResult();
    }

    private SwapiPlanetPaginatedResponseDTO buildPaginatedResponse(List<SwapiPlanetWrapperDTO> all, int page, int size) {
        SwapiPlanetPaginatedResponseDTO response = new SwapiPlanetPaginatedResponseDTO();

        int sizeLimit = size == 0 ? all.size() : size;

        int fromIndex = PaginationCalculation.calculateFromIndex(page, sizeLimit);
        int toIndex = PaginationCalculation.calculateToIndex(fromIndex, sizeLimit, all.size());
        int totalPages = PaginationCalculation.calculateTotalPages(all.size(), sizeLimit);

        List<SwapiPlanetWrapperDTO> paginated = all.subList(fromIndex, toIndex);

        response.setTotal_records(all.size());
        response.setTotal_pages(totalPages);
        response.setResults(paginated);
        return response;
    }
}
