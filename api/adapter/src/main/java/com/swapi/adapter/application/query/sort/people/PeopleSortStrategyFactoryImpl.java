package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.sort.SortStrategy;
import com.swapi.adapter.application.query.sort.common.NoOpSort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PeopleSortStrategyFactoryImpl implements PeopleSortStrategyFactory {

    private final Map<String, SortStrategy<PeopleResponseDTO>> strategies;

    public PeopleSortStrategyFactoryImpl(
            @Qualifier("PeopleSortByName") SortStrategy<PeopleResponseDTO> nameStrategy,
            @Qualifier("PeopleSortByCreatedDate") SortStrategy<PeopleResponseDTO> createdStrategy
    ) {
        this.strategies = Map.of(
                "name", nameStrategy,
                "created", createdStrategy
        );
    }

    @Override
    public SortStrategy<PeopleResponseDTO> getStrategy(String sortBy) {
        return strategies.getOrDefault(sortBy, new NoOpSort<>());
    }
}
