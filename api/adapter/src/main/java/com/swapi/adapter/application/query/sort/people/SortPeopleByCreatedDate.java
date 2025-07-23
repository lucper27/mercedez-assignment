package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.sort.SortStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
@Qualifier("PeopleSortByCreatedDate")
public class SortPeopleByCreatedDate implements SortStrategy<PeopleResponseDTO> {

    @Override
    public List<PeopleResponseDTO> sort(List<PeopleResponseDTO> data, boolean ascending) {
        Comparator<PeopleResponseDTO> comparator = Comparator.comparing(dto -> {
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
