package com.swapi.adapter.application.query.sort.planet;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.mapper.SwapiPlanetResponseMapper;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.SortStrategy;
import com.swapi.adapter.application.query.sort.common.QueryParameters;
import com.swapi.adapter.application.query.sort.common.utils.PaginationCalculation;
import com.swapi.adapter.application.query.sort.common.utils.SortDirectionUtils;
import com.swapi.adapter.infrastructure.client.swapi.SwapiPlanetClient;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiBasePaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetPaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetWrapperDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetSortedQueryImpl implements PlanetSortedQuery {

    private final Logger log = LoggerFactory.getLogger(PlanetSortedQueryImpl.class);

    private final int MINIMUM = 1;

    private final SwapiPlanetClient swapiPlanetClient;

    private final SwapiPlanetResponseMapper swapiPlanetResponseMapper;

    private final PlanetSortStrategyFactory planetSortStrategyFactory;

    public PlanetSortedQueryImpl(SwapiPlanetClient swapiPlanetClient, SwapiPlanetResponseMapper swapiPlanetResponseMapper, PlanetSortStrategyFactory planetSortStrategyFactory) {
        this.swapiPlanetClient = swapiPlanetClient;
        this.swapiPlanetResponseMapper = swapiPlanetResponseMapper;
        this.planetSortStrategyFactory = planetSortStrategyFactory;
    }

    @Override
    public PaginatedResponse<PlanetResponseDTO> execute(QueryParameters params) {
        log.debug("sorted planet query with params {}", params);
        String name = params.getName();

        List<SwapiPlanetWrapperDTO> results;

        if (name != null && !name.isBlank()) {
            results = swapiPlanetClient.searchByName(name);
        } else {
            int totalItems = swapiPlanetClient.query(MINIMUM, MINIMUM, "").getTotal_records();
            results = swapiPlanetClient.query(MINIMUM, totalItems, "").getResults();
        }
        
        List<PlanetResponseDTO> sorted = sortResponse(params.getSort(), params.getDirection(), results);

        int fromIndex = PaginationCalculation.calculateFromIndex(params.getPage(), params.getSize());
        int toIndex = PaginationCalculation.calculateToIndex(fromIndex, params.getSize(), sorted.size());

        List<PlanetResponseDTO> paginated = sorted.subList(fromIndex, toIndex);

        PaginatedResponse<PlanetResponseDTO> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(paginated);
        paginatedResponse.setTotalElements(sorted.size());
        paginatedResponse.setTotalPages(PaginationCalculation.calculateTotalPages(sorted.size(), params.getSize()));
        paginatedResponse.setPage(params.getPage());
        paginatedResponse.setSize(params.getSize());

        return paginatedResponse;
    }

    private List<PlanetResponseDTO> sortResponse(String sort, String directionOrder, List<SwapiPlanetWrapperDTO> rawResults) {
        log.debug("sorting response....");

        List<PlanetResponseDTO> planetResponseDTOS = rawResults
                .stream()
                .map(swapiPlanetResponseMapper::toDto)
                .toList();

        SortStrategy<PlanetResponseDTO> sortStrategy = planetSortStrategyFactory.getStrategy(sort);
        boolean ascending = SortDirectionUtils.isAscending(directionOrder);

        return sortStrategy.sort(planetResponseDTOS, ascending);
    }
}
