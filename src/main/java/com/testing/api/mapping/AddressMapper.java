package com.testing.api.mapping;

import com.testing.api.resource.PersonAddressApi;
import com.testing.dto.PersonAddressDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

public class AddressMapper {

    private AddressMapper() {}

    static ModelMapper modelMapper = new ModelMapper();

    public static PersonAddressDto mapAddressToDto(PersonAddressApi personAddressApi) {
        return personAddressApi != null ? modelMapper.map(personAddressApi, PersonAddressDto.class) : null ;
    }

    public static PersonAddressApi mapAddressDtoToApi(PersonAddressDto personAddressDto) {
        return personAddressDto != null ? modelMapper.map(personAddressDto, PersonAddressApi.class) : null;
    }
}
