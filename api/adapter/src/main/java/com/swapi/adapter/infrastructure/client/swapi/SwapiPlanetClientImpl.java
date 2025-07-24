package com.swapi.adapter.infrastructure.client.swapi;

import com.swapi.adapter.application.exception.PlanetClientException;
import com.swapi.adapter.application.query.sort.common.utils.PaginationCalculation;
import com.swapi.adapter.infrastructure.client.swapi.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class SwapiPlanetClientImpl implements SwapiPlanetClient {

    private final Logger log = LoggerFactory.getLogger(SwapiPlanetClientImpl.class);

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
            log.debug("query planets with name filter {}, page {}, and size {}", name, page, size);
            List<SwapiPlanetWrapperDTO> filtered = searchByName(name);
            return buildPaginatedResponse(filtered, page, size);
        }

        log.debug("query planets with  page {}, and size {}", page, size);
        return getAllPaginated(page, size);
    }

    @Override
    public SwapiPlanetPaginatedResponseDTO getAllPaginated (int page, int size) {
        log.debug("connecting with swapi, requested page {}, requested size {}", page, size);
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl(baseUrl + "/planets")
                    .queryParam("expanded", "true")
                    .queryParam("page", page)
                    .queryParam("limit", size)
                    .toUriString();

            SwapiPlanetPaginatedResponseDTO response = restTemplate.getForObject(url, SwapiPlanetPaginatedResponseDTO.class);

            if (response == null) {
                log.error("null response from swapi");
                throw new PlanetClientException("Received null response from SWAPI planets endpoint.");
            }

            return response;
        } catch (Exception e) {
            log.error("error while connecting with swapi for planets {}", e.getMessage());
            throw new PlanetClientException(e.getMessage());
        }
    }

    @Override
    public List<SwapiPlanetWrapperDTO> searchByName (String name) {
        log.debug("connecting with swapi, requested name {}", name);
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl(baseUrl + "/planets")
                    .queryParam("expanded", "true")
                    .queryParam("name", name)
                    .toUriString();

            SwapiPlanetSearchResponseDTO response = restTemplate.getForObject(url, SwapiPlanetSearchResponseDTO.class);

            return response != null ? response.getResult() : List.of();
        } catch (Exception e) {
            log.error("error while connecting with swapi for planets {}", e.getMessage());
            throw new PlanetClientException(e.getMessage());
        }
    }

    private SwapiPlanetPaginatedResponseDTO buildPaginatedResponse(List<SwapiPlanetWrapperDTO> all, int page, int size) {
        log.debug("building paginated response with response {}, page {}, and size {}", all, page, size);
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
