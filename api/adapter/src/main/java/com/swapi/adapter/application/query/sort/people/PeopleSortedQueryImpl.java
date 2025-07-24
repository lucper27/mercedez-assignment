package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.mapper.SwapiPeopleResponseMapper;
import com.swapi.adapter.application.query.pagination.PaginatedResponse;
import com.swapi.adapter.application.query.sort.SortStrategy;
import com.swapi.adapter.application.query.sort.common.QueryParameters;
import com.swapi.adapter.application.query.sort.common.utils.PaginationCalculation;
import com.swapi.adapter.application.query.sort.common.utils.SortDirectionUtils;
import com.swapi.adapter.infrastructure.client.swapi.SwapiPeopleClient;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiBasePaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPeoplePaginatedResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PeopleSortedQueryImpl implements PeopleSortedQuery {

    private final int MINIMUM = 1;

    private final SwapiPeopleClient swapiPeopleClient;

    private final SwapiPeopleResponseMapper swapiPeopleResponseMapper;

    private final PeopleSortStrategyFactory peopleSortStrategyFactory;

    public PeopleSortedQueryImpl(SwapiPeopleClient swapiPeopleClient, SwapiPeopleResponseMapper swapiPeopleResponseMapper, PeopleSortStrategyFactory peopleSortStrategyFactory) {
        this.swapiPeopleClient = swapiPeopleClient;
        this.swapiPeopleResponseMapper = swapiPeopleResponseMapper;
        this.peopleSortStrategyFactory = peopleSortStrategyFactory;
    }

    @Override
    public PaginatedResponse<PeopleResponseDTO> execute(QueryParameters params) {
        SwapiBasePaginatedResponseDTO response = swapiPeopleClient.query(MINIMUM, MINIMUM, "");
        int totalItems = response.getTotal_records();

        SwapiPeoplePaginatedResponseDTO responseWithAllData = swapiPeopleClient.query(MINIMUM, totalItems, "");

        List<PeopleResponseDTO> sorted = sortResponse(params.getSort(), params.getDirection(), responseWithAllData);

        int fromIndex = PaginationCalculation.calculateFromIndex(params.getPage(), params.getSize());
        int toIndex = PaginationCalculation.calculateToIndex(fromIndex, params.getSize(), sorted.size());

        List<PeopleResponseDTO> paginated = sorted.subList(fromIndex, toIndex);

        PaginatedResponse<PeopleResponseDTO> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setContent(paginated);
        paginatedResponse.setTotalElements(responseWithAllData.getTotal_records());
        paginatedResponse.setTotalPages(responseWithAllData.getTotal_pages());
        paginatedResponse.setPage(params.getPage());
        paginatedResponse.setSize(params.getSize());

        return paginatedResponse;

    }

    private List<PeopleResponseDTO> sortResponse(String sort, String directionOrder, SwapiPeoplePaginatedResponseDTO responseFromAPI) {
        List<PeopleResponseDTO> peopleResponseDTOS = responseFromAPI.getResults()
                .stream()
                .map(swapiPeopleResponseMapper::toDto)
                .toList();

        SortStrategy<PeopleResponseDTO> sortStrategy = peopleSortStrategyFactory.getStrategy(sort);
        boolean ascending = SortDirectionUtils.isAscending(directionOrder);

        return sortStrategy.sort(peopleResponseDTOS, ascending);
    }
}
