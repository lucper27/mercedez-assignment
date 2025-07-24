package com.swapi.adapter.application.mapper;


import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPersonWrapperDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SwapiPeopleResponseMapper extends EntityMapper<PeopleResponseDTO, SwapiPersonWrapperDTO> {

    @Mapping(target = "name", source = "properties.name")
    @Mapping(target = "gender", source = "properties.gender")
    @Mapping(target = "skinColor", source = "properties.skin_color")
    @Mapping(target = "hairColor", source = "properties.hair_color")
    @Mapping(target = "height", source = "properties.height")
    @Mapping(target = "eyeColor", source = "properties.eye_color")
    @Mapping(target = "mass", source = "properties.mass")
    @Mapping(target = "homeworld", source = "properties.homeworld")
    @Mapping(target = "birthYear", source = "properties.birth_year")
    @Mapping(target = "created", source = "properties.created")
    @Mapping(target = "edited", source = "properties.edited")
    @Mapping(target = "url", source = "properties.url")
    PeopleResponseDTO toDto(SwapiPersonWrapperDTO swapiPersonWrapperDTO);
}
