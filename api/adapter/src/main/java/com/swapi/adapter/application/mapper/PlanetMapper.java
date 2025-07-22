package com.swapi.adapter.application.mapper;

import com.swapi.adapter.application.dto.PlanetResponseDTO;
import com.swapi.adapter.domain.entity.Planet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanetMapper extends EntityMapper<PlanetResponseDTO, Planet> {

    PlanetResponseDTO toDto(Planet planet);
}
