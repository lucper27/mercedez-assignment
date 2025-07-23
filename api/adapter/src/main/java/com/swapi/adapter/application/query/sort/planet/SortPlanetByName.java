package com.swapi.adapter.application.query.sort.planet;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.sort.SortStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Qualifier("PlanetSortByName")
public class SortPlanetByName implements SortStrategy<PlanetResponseDTO> {


    @Override
    public List<PlanetResponseDTO> sort(List<PlanetResponseDTO> data, boolean ascending) {
        Comparator<PlanetResponseDTO> comparator = Comparator
                .comparing(PlanetResponseDTO::getName, String.CASE_INSENSITIVE_ORDER);

        return ascending ? data.stream().sorted(comparator).toList()
                : data.stream().sorted(comparator.reversed()).toList();
    }
}
