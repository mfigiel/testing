package com.testing.api.mapping;

import com.testing.api.resource.PersonApi;
import com.testing.dto.PersonDto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {

    private PersonMapper() {
        throw new IllegalStateException("Utility class");
    }

    static ModelMapper modelMapper = new ModelMapper();

    public static PersonDto mapToDto(PersonApi personApi) {
        return personApi != null ? modelMapper.map(personApi, PersonDto.class) : null;
    }

    public static PersonApi mapToPerson(PersonDto personDto) {
        return personDto != null ? modelMapper.map(personDto, PersonApi.class) : null;
    }

    public static List<PersonApi> mapToListOfPersons(List<PersonDto> personDtoList) {
        List<PersonApi> personApiList = new ArrayList<>();
        for (PersonDto personDto : personDtoList) {
            personApiList.add(mapToPerson(personDto));
        }
        return personApiList;
    }
}
