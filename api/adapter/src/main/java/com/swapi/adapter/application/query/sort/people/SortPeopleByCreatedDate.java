package com.swapi.adapter.application.query.sort.people;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.application.query.sort.SortStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
@Qualifier("PeopleSortByCreatedDate")
public class SortPeopleByCreatedDate implements SortStrategy<PeopleResponseDTO> {

    private final Logger log = LoggerFactory.getLogger(SortPeopleByCreatedDate.class);

    @Override
    public List<PeopleResponseDTO> sort(List<PeopleResponseDTO> data, boolean ascending) {
        log.debug("sorting people by created date with data {}, and ascending value = {}", data, ascending);
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
