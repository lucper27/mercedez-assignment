package com.swapi.adapter.application.query.sort.common.utils;

public class PaginationCalculation {

    public static int calculateFromIndex(int page, int size) {
        return Math.max((page - 1) * size, 0);
    }

    public static int calculateToIndex(int fromIndex, int size, int totalSize) {
        return Math.min(fromIndex + size, totalSize);
    }
}
