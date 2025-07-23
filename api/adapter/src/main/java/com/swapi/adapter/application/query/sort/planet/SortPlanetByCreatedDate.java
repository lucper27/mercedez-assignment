package com.swapi.adapter.application.query.sort.planet;


import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.application.query.sort.SortStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
@Qualifier("PlanetSortByCreatedDate")
public class SortPlanetByCreatedDate implements SortStrategy<PlanetResponseDTO> {


    @Override
    public List<PlanetResponseDTO> sort(List<PlanetResponseDTO> data, boolean ascending) {
        Comparator<PlanetResponseDTO> comparator = Comparator.comparing(dto -> {
            try {
                return Instant.parse(dto.getCreated());
            } catch (Exception e) {
                return Instant.EPOCH;
            }
        });

        return ascending
                ? data.stream().sorted(comparator).toList()
                : data.stream().sorted(comparator.reversed()).toList();
    }
}
