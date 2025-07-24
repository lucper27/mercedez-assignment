package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.sort.SortStrategy;

public interface PeopleSortStrategyFactory {

    SortStrategy<PeopleResponseDTO> getStrategy(String sortBy);
}
