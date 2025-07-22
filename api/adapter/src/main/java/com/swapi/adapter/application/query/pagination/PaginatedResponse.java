package com.swapi.adapter.application.query.pagination;

import java.util.List;

public class PaginatedResponse<T> {

    private List<T> content;

    private int page;

    private int size;

    private long totalElements;

    private int totalPages;
}

