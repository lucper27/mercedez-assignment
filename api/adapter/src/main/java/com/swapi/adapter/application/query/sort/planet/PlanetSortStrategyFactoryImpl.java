package com.swapi.adapter.application.query.sort.planet;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.sort.SortStrategy;
import com.swapi.adapter.application.query.sort.common.NoOpSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PlanetSortStrategyFactoryImpl implements PlanetSortStrategyFactory {

    private final Logger log = LoggerFactory.getLogger(PlanetSortStrategyFactoryImpl.class);

    private final Map<String, SortStrategy<PlanetResponseDTO>> strategies;

    public PlanetSortStrategyFactoryImpl(@Qualifier("PlanetSortByName") SortStrategy<PlanetResponseDTO> nameStrategy,
                                         @Qualifier("PlanetSortByCreatedDate") SortStrategy<PlanetResponseDTO> createdStrategy) {
        this.strategies = Map.of(
                "name", nameStrategy,
                "created", createdStrategy
        );
    }


    @Override
    public SortStrategy<PlanetResponseDTO> getStrategy(String sortBy) {
        log.debug("getting sorting strategy for {}", sortBy);
        return strategies.getOrDefault(sortBy, new NoOpSort<>());
    }
}
