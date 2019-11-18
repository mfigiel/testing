package com.testing.api.mapping;

import com.testing.api.resource.PersonApi;
import com.testing.dto.PersonDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "PersonApiPersonDtoMapper")
public interface PersonApiPersonDtoMapper {
    PersonApi personDtoToPersonApi(PersonDto source);
    PersonDto personApiToPersonDto(PersonApi source);
    List<PersonDto> personApiListToPersonDtoList(List<PersonApi> source);
    List<PersonApi> personDtoListToPersonApiList(List<PersonDto> source);
}

