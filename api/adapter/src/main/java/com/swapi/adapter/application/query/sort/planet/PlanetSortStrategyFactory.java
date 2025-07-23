package com.swapi.adapter.application.query.sort.planet;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.sort.SortStrategy;

public interface PlanetSortStrategyFactory {

    SortStrategy<PlanetResponseDTO> getStrategy(String sortBy);
}
