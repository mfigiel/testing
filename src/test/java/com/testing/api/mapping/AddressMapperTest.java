package com.testing.api.mapping;

import com.testing.api.resource.PersonAddressApi;
import com.testing.dto.PersonAddressDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class AddressMapperTest {

    PersonAddressApi personAddressApi = new PersonAddressApi();
    PersonAddressDto personAddressDto = new PersonAddressDto();

    @Before
    public void init() {
        personAddressApi.setCity("Gliwice");
        personAddressApi.setHouseNumber(5);
        personAddressApi.setStreet("Street");
        personAddressApi.setZipCode("44-100");
        personAddressApi.setFlatNumber(4);

        personAddressDto.setCity("Gliwice");
        personAddressDto.setHouseNumber(5);
        personAddressDto.setStreet("Street");
        personAddressDto.setZipCode("44-100");
        personAddressDto.setFlatNumber(4);
    }

    @Test
    public void lambda(){
        List<String> names = Arrays.asList("bard", "la", "of", "bank");
            Collections.sort(names, (s1, s2) -> s1.compareToIgnoreCase(s2));
            names.forEach(arg -> System.out.println(arg));
    }

    @Test
    public void mapAddressToDto(){
        PersonAddressDto personAddressDto = AddressMapper.mapAddressToDto(personAddressApi);

        //then
        assertThat("Invalid mapper result", personAddressDto, is(notNullValue()));
        assertThat("Invalid mapper result", personAddressDto.getCreationDate().toString(), is(notNullValue()));
        assertThat("Invalid mapper result", personAddressDto.getCity(), is("Gliwice"));
        assertThat("Invalid mapper result", personAddressDto.getHouseNumber(), is(5));
        assertThat("Invalid mapper result", personAddressDto.getStreet(), is("Street"));
        assertThat("Invalid mapper result", personAddressDto.getZipCode(), is("44-100"));
        assertThat("Invalid mapper result", personAddressDto.getHouseNumber(), is(5));
        assertThat("Invalid mapper result", personAddressDto.getId(), is(nullValue()));
        assertThat("Invalid mapper result", personAddressDto.toString(),
                is("PersonAddressDto{id=null, creationDate="+personAddressDto.getCreationDate().toString()+", city='Gliwice', street='Street', houseNumber=5, flatNumber=4, zipCode='44-100'}"));
    }

    @Test
    public void mapAdressToApi() {
        PersonAddressApi personAddressApi = AddressMapper.mapAddressDtoToApi(personAddressDto);

        //then
        assertThat("Invalid mapper result", personAddressApi, is(notNullValue()));
        assertThat("Invalid mapper result", personAddressApi.getCity(), is("Gliwice"));
        assertThat("Invalid mapper result", personAddressApi.getHouseNumber(), is(5));
        assertThat("Invalid mapper result", personAddressApi.getStreet(), is("Street"));
        assertThat("Invalid mapper result", personAddressApi.getZipCode(), is("44-100"));
        assertThat("Invalid mapper result", personAddressApi.getHouseNumber(), is(5));
        assertThat("Invalid mapper result", personAddressApi.toString(),
                is("PersonAddressApi{city='Gliwice', street='Street', houseNumber=5, flatNumber=4, zipCode='44-100'}"));
    }
}
