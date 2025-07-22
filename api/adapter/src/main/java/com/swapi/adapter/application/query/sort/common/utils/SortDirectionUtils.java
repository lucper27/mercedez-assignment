package com.swapi.adapter.application.query.sort.common.utils;

public class SortDirectionUtils {

    public static boolean isAscending(String direction) {
        return !"desc".equalsIgnoreCase(direction);
    }
}
