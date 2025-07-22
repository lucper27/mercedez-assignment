package com.swapi.adapter.application.query.sort;

import java.util.List;

public interface SortStrategy<T> {

    List<T> sort(List<T> data, boolean ascending);

}
