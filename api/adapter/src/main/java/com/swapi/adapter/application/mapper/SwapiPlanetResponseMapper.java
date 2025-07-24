package com.swapi.adapter.application.mapper;


import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetWrapperDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SwapiPlanetResponseMapper extends EntityMapper<PlanetResponseDTO, SwapiPlanetWrapperDTO> {

    @Mapping(target = "climate", source = "properties.climate")
    @Mapping(target = "created", source = "properties.created")
    @Mapping(target = "diameter", source = "properties.diameter")
    @Mapping(target = "edited", source = "properties.edited")
    @Mapping(target = "gravity", source = "properties.gravity")
    @Mapping(target = "name", source = "properties.name")
    @Mapping(target = "orbitalPeriod", source = "properties.orbital_period")
    @Mapping(target = "population", source = "properties.population")
    @Mapping(target = "rotationPeriod", source = "properties.rotation_period")
    @Mapping(target = "surfaceWater", source = "properties.surface_water")
    @Mapping(target = "terrain", source = "properties.terrain")
    PlanetResponseDTO toDto(SwapiPlanetWrapperDTO swapiPlanetWrapperDTO);

}
