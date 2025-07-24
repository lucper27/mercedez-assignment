package com.swapi.adapter.infrastructure.client.swapi;

import com.swapi.adapter.application.exception.PeopleClientException;
import com.swapi.adapter.application.query.sort.common.utils.PaginationCalculation;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPeoplePaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPersonWrapperDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiSearchPeopleResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class SwapiPeopleClientImpl implements SwapiPeopleClient {

    private final Logger log = LoggerFactory.getLogger(SwapiPeopleClientImpl.class);

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
            log.debug("query people with name filter {}, page {}, and size {}", name, page, size);
                List<SwapiPersonWrapperDTO> filtered = searchByName(name);
                return buildPaginatedResponse(filtered, page, size);
        }

        log.debug("query people with  page {}, and size {}", page, size);
        return getAllPaginated(page, size);
    }

    @Override
    public SwapiPeoplePaginatedResponseDTO getAllPaginated(int page, int size) {
        log.debug("connecting with swapi, requested page {}, requested size {}", page, size);
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl(baseUrl + "/people")
                    .queryParam("expanded", "true")
                    .queryParam("page", page)
                    .queryParam("limit", size)
                    .toUriString();

            SwapiPeoplePaginatedResponseDTO response = restTemplate.getForObject(url, SwapiPeoplePaginatedResponseDTO.class);

            if (response == null) {
                log.error("null response from swapi");
                throw new PeopleClientException("Received null response from SWAPI planets endpoint.");
            }

            return response;
        } catch (Exception e) {
            log.error("error while connecting with swapi for people {}", e.getMessage());
            throw new PeopleClientException(e.getMessage());
        }
    }

    @Override
    public List<SwapiPersonWrapperDTO> searchByName(String name) {
        log.debug("connecting with swapi, requested name {}", name);
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl(baseUrl + "/people")
                    .queryParam("expanded", "true")
                    .queryParam("name", name)
                    .toUriString();

            SwapiSearchPeopleResponseDTO response = restTemplate.getForObject(url, SwapiSearchPeopleResponseDTO.class);

            return response != null ? response.getResult() : List.of();
        } catch(Exception e) {
            log.error("error while connecting with swapi for people {}", e.getMessage());
            throw new PeopleClientException(e.getMessage());
        }
    }

    private SwapiPeoplePaginatedResponseDTO buildPaginatedResponse(List<SwapiPersonWrapperDTO> all, int page, int size) {
        log.debug("building paginated response with response {}, page {}, and size {}", all, page, size);
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
