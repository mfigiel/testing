package com.testing.api.mapping;

import com.testing.api.resource.PersonAddressApi;
import com.testing.api.resource.PersonApi;
import com.testing.dto.PersonDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PersonMapperTestMapStruct {

    private PersonApiPersonDtoMapper mapper
            = Mappers.getMapper(PersonApiPersonDtoMapper.class);

    PersonApi personApi = new PersonApi();
    PersonDto personDto = new PersonDto();
    PersonAddressApi personAddressApi = new PersonAddressApi();

    @Before
    public void init() throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        personApi.setName("Name");
        personApi.setSurname("Surname");

        personDto.setCreationDate(date.parse("17/07/1989"));
        personDto.setName("Name");
        personDto.setId(1L);
        personDto.setSurname("Surname");

        personAddressApi.setCity("Gliwice");
        personAddressApi.setHouseNumber(5);
        personAddressApi.setStreet("Street");
        personAddressApi.setZipCode("44-100");
    }

    @Test
    public void testMapToDto() {

        PersonDto personDto = mapper.personApiToPersonDto(personApi);

        //then
        assertThat("Invalid mapper result", personDto, is(notNullValue()));
        assertThat("Invalid mapper result", personDto.getCreationDate().toString(), is(notNullValue()));
        assertThat("Invalid mapper result", personDto.getName(), is("Name"));
        assertThat("Invalid mapper result", personDto.getSurname(), is("Surname"));
        assertThat("Invalid mapper result", personDto.getId(), is(nullValue()));

    }

    @Test
    public void testMapToApi() {
        PersonApi personApi = mapper.personDtoToPersonApi(personDto);

        //then
        assertThat("Invalid mapper result", personApi, is(notNullValue()));
        assertThat("Invalid mapper result", personApi.getName(), is("Name"));
        assertThat("Invalid mapper result", personApi.getSurname(), is("Surname"));
        assertThat("Invalid mapper result", personApi.toString(),
                is("PersonApi{name='Name', surname='Surname', address=null}"));

    }

    @Test
    public void testMapToDtoWithConstructor() {
        PersonDto personDto = mapper.personApiToPersonDto(personApi);

        //then
        assertThat("Invalid mapper result", personDto, is(notNullValue()));
        assertThat("Invalid mapper result", personDto.getCreationDate().toString(), is(notNullValue()));
        assertThat("Invalid mapper result", personDto.getName(), is("Name"));
        assertThat("Invalid mapper result", personDto.getSurname(), is("Surname"));
        assertThat("Invalid mapper result", personDto.getAddress(), is(nullValue()));
        assertThat("Invalid mapper result", personDto.toString(),
                is("PersonDto{id=null, creationDate="+personDto.getCreationDate().toString()+", name='Name', surname='Surname', address=null}"));

    }

    @Test
    public void testMapToDtoList() {
        List<PersonDto> personDtoList = new ArrayList<>();
        personDtoList.add(personDto);
        List<PersonApi> personApiList = mapper.personDtoListToPersonApiList(personDtoList);

        //then
        assertThat("Invalid mapper result", personApiList, is(notNullValue()));
        assertThat("Invalid mapper result", personApiList.size(), is(1));
        assertThat("Invalid mapper result", personApiList.get(0).getName(), is("Name"));
        assertThat("Invalid mapper result", personApiList.get(0).getSurname(), is("Surname"));

    }

    @Test
    public void testMapToDtoWithConstructorAndAdress() {
        personApi.setAddress(personAddressApi);
        PersonDto personDto = mapper.personApiToPersonDto(personApi);
        //then
        assertThat("Invalid mapper result", personDto, is(notNullValue()));
        assertThat("Invalid mapper result", personDto.getCreationDate().toString(), is(notNullValue()));
        assertThat("Invalid mapper result", personDto.getName(), is("Name"));
        assertThat("Invalid mapper result", personDto.getSurname(), is("Surname"));
        assertThat("Invalid mapper result", personDto.getAddress(), is(notNullValue()));
        assertThat("Invalid mapper result", personDto.getAddress().getCity(), is("Gliwice"));
        assertThat("Invalid mapper result", personDto.getAddress().getStreet(), is("Street"));
        assertThat("Invalid mapper result", personDto.getAddress().getZipCode(), is("44-100"));
        assertThat("Invalid mapper result", personDto.getAddress().getHouseNumber(), is(5));
        assertThat("Invalid mapper result", personDto.getAddress().getCreationDate(), is(notNullValue()));
        assertThat("Invalid mapper result", personDto.getAddress().getId(), is(nullValue()));

    }

}
