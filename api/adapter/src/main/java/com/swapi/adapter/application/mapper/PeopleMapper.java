package com.swapi.adapter.application.mapper;

import com.swapi.adapter.application.dto.PeopleResponseDTO;
import com.swapi.adapter.domain.entity.People;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeopleMapper extends EntityMapper<PeopleResponseDTO, People> {

    PeopleResponseDTO toDto(People people);
}
