package com.swapi.adapter.infraestructure.client.swapi;

import com.swapi.adapter.application.query.sort.common.utils.PaginationCalculation;
import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPaginatedResponseDTO;
import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiPersonWrapperDTO;
import com.swapi.adapter.infraestructure.client.swapi.dto.SwapiSearchResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class SwapiPeopleClientImpl implements SwapiPeopleClient {

    private final RestTemplate restTemplate;

    public SwapiPeopleClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public SwapiPaginatedResponseDTO query(int page, int size, String name) {
        boolean nameFilterPresent = name != null && !name.isEmpty() ;

        if (nameFilterPresent) {
                List<SwapiPersonWrapperDTO> filtered = searchByName(name);
                return buildPaginatedResponse(filtered, page, size);
        }

        return getAllPaginated(page, size);
    }

    @Override
    public SwapiPaginatedResponseDTO getAllPaginated(int page, int size) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://www.swapi.tech/api/people")
                .queryParam("expanded", "true")
                .queryParam("page", page)
                .queryParam("limit", size)
                .toUriString();

        SwapiPaginatedResponseDTO response = restTemplate.getForObject(url, SwapiPaginatedResponseDTO.class);

        return response;
    }

    @Override
    public List<SwapiPersonWrapperDTO> searchByName(String name) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://www.swapi.tech/api/people")
                .queryParam("expanded", "true")
                .queryParam("name", name)
                .toUriString();

        SwapiSearchResponseDTO response = restTemplate.getForObject(url, SwapiSearchResponseDTO.class);
        return response.getResult();
    }

    private SwapiPaginatedResponseDTO buildPaginatedResponse(List<SwapiPersonWrapperDTO> all, int page, int size) {
        SwapiPaginatedResponseDTO response = new SwapiPaginatedResponseDTO();

        int fromIndex = PaginationCalculation.calculateFromIndex(page, size);
        int toIndex = PaginationCalculation.calculateToIndex(fromIndex, size, all.size());
        int totalPages = PaginationCalculation.calculateTotalPages(all.size(), size);

        List<SwapiPersonWrapperDTO> paginated = all.subList(fromIndex, toIndex);

        response.setTotal_records(all.size());
        response.setTotal_pages(totalPages);
        response.setResults(paginated);
        return response;
    }

}
