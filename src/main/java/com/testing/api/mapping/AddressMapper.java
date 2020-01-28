package com.testing.api.mapping;

import com.testing.api.resource.AddressApi;
import com.testing.dto.PersonAddressDto;
import org.modelmapper.ModelMapper;

public class AddressMapper {

    private AddressMapper() {}

    static ModelMapper modelMapper = new ModelMapper();

    public static PersonAddressDto mapAddressToDto(AddressApi addressApi) {
        return addressApi != null ? modelMapper.map(addressApi, PersonAddressDto.class) : null ;
    }

    public static AddressApi mapAddressDtoToApi(PersonAddressDto personAddressDto) {
        return personAddressDto != null ? modelMapper.map(personAddressDto, AddressApi.class) : null;
    }
}
