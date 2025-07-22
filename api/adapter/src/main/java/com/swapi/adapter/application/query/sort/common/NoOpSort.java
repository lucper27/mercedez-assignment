package com.swapi.adapter.application.query.sort.common;

import com.swapi.adapter.application.query.sort.SortStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoOpSort<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> data, boolean ascending) {
        return data;
    }
}
