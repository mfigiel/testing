package com.testing.api.mapping;

import com.testing.api.resource.PersonAddressApi;
import com.testing.api.resource.PersonApi;
import com.testing.dto.PersonAddressDto;
import com.testing.dto.PersonDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-01T19:36:53+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class PersonApiPersonDtoMapperImpl implements PersonApiPersonDtoMapper {

    @Override
    public PersonApi personDtoToPersonApi(PersonDto source) {
        if ( source == null ) {
            return null;
        }

        PersonApi personApi = new PersonApi();

        personApi.setName( source.getName() );
        personApi.setSurname( source.getSurname() );
        personApi.setAddress( personAddressDtoToPersonAddressApi( source.getAddress() ) );

        return personApi;
    }

    @Override
    public PersonDto personApiToPersonDto(PersonApi source) {
        if ( source == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setName( source.getName() );
        personDto.setSurname( source.getSurname() );
        personDto.setAddress( personAddressApiToPersonAddressDto( source.getAddress() ) );

        return personDto;
    }

    @Override
    public List<PersonDto> personApiListToPersonDtoList(List<PersonApi> source) {
        if ( source == null ) {
            return null;
        }

        List<PersonDto> list = new ArrayList<PersonDto>( source.size() );
        for ( PersonApi personApi : source ) {
            list.add( personApiToPersonDto( personApi ) );
        }

        return list;
    }

    @Override
    public List<PersonApi> personDtoListToPersonApiList(List<PersonDto> source) {
        if ( source == null ) {
            return null;
        }

        List<PersonApi> list = new ArrayList<PersonApi>( source.size() );
        for ( PersonDto personDto : source ) {
            list.add( personDtoToPersonApi( personDto ) );
        }

        return list;
    }

    protected PersonAddressApi personAddressDtoToPersonAddressApi(PersonAddressDto personAddressDto) {
        if ( personAddressDto == null ) {
            return null;
        }

        PersonAddressApi personAddressApi = new PersonAddressApi();

        personAddressApi.setCity( personAddressDto.getCity() );
        personAddressApi.setStreet( personAddressDto.getStreet() );
        personAddressApi.setHouseNumber( personAddressDto.getHouseNumber() );
        personAddressApi.setFlatNumber( personAddressDto.getFlatNumber() );
        personAddressApi.setZipCode( personAddressDto.getZipCode() );

        return personAddressApi;
    }

    protected PersonAddressDto personAddressApiToPersonAddressDto(PersonAddressApi personAddressApi) {
        if ( personAddressApi == null ) {
            return null;
        }

        PersonAddressDto personAddressDto = new PersonAddressDto();

        personAddressDto.setCity( personAddressApi.getCity() );
        personAddressDto.setStreet( personAddressApi.getStreet() );
        personAddressDto.setHouseNumber( personAddressApi.getHouseNumber() );
        personAddressDto.setFlatNumber( personAddressApi.getFlatNumber() );
        personAddressDto.setZipCode( personAddressApi.getZipCode() );

        return personAddressDto;
    }
}
