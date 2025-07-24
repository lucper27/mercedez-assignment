package com.swapi.adapter.util;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetPaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetSearchResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetWrapperDTO;

import java.util.List;

public class PlanetMockData {

    public static PlanetResponseDTO tatooineResponse() {
        PlanetResponseDTO dto = new PlanetResponseDTO();
        dto.setName("Tatooine");
        dto.setClimate("arid");
        dto.setCreated("2025-07-22T16:28:46.488Z");
        dto.setDiameter("10465");
        dto.setEdited("2025-07-22T16:28:46.488Z");
        dto.setGravity("1 standard");
        dto.setOrbitalPeriod("304");
        dto.setPopulation("200000");
        dto.setRotationPeriod("23");
        dto.setSurfaceWater("1");
        dto.setTerrain("desert");
        return dto;
    }

    public static SwapiPlanetWrapperDTO tatooineWrapper() {
        SwapiPlanetWrapperDTO wrapper = new SwapiPlanetWrapperDTO();
        SwapiPlanetDTO dto = new SwapiPlanetDTO();
        dto.setName("Tatooine");
        dto.setClimate("arid");
        dto.setCreated("2025-07-22T16:28:46.488Z");
        dto.setEdited("2025-07-22T16:28:46.488Z");
        dto.setDiameter("10465");
        dto.setGravity("1 standard");
        dto.setOrbital_period("304");
        dto.setPopulation("200000");
        dto.setRotation_period("23");
        dto.setSurface_water("1");
        dto.setTerrain("desert");
        wrapper.setProperties(dto);
        return wrapper;
    }

    public static SwapiPlanetPaginatedResponseDTO swapiPlanetResponse() {
        SwapiPlanetPaginatedResponseDTO response = new SwapiPlanetPaginatedResponseDTO();
        response.setTotal_records(1);
        response.setTotal_pages(1);
        response.setResults(List.of(tatooineWrapper()));
        return response;
    }

    public static SwapiPlanetSearchResponseDTO swapiPlanetSearchResponse() {
        SwapiPlanetSearchResponseDTO response = new SwapiPlanetSearchResponseDTO();
        response.setResult(List.of(tatooineWrapper()));
        return response;
    }
}
