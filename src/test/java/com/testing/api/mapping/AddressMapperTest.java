package com.testing.api.mapping;

import com.testing.api.resource.AddressApi;
import com.testing.dto.PersonAddressDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(MockitoJUnitRunner.class)
public class AddressMapperTest {

    AddressApi addressApi = new AddressApi();
    PersonAddressDto personAddressDto = new PersonAddressDto();

    @Before
    public void init() {
        addressApi.setCity("Gliwice");
        addressApi.setHouseNumber(5);
        addressApi.setStreet("Street");
        addressApi.setZipCode("44-100");
        addressApi.setFlatNumber(4);

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
        PersonAddressDto personAddressDto = AddressMapper.mapAddressToDto(addressApi);

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
        AddressApi addressApi = AddressMapper.mapAddressDtoToApi(personAddressDto);

        //then
        assertThat("Invalid mapper result", addressApi, is(notNullValue()));
        assertThat("Invalid mapper result", addressApi.getCity(), is("Gliwice"));
        assertThat("Invalid mapper result", addressApi.getHouseNumber(), is(5));
        assertThat("Invalid mapper result", addressApi.getStreet(), is("Street"));
        assertThat("Invalid mapper result", addressApi.getZipCode(), is("44-100"));
        assertThat("Invalid mapper result", addressApi.getHouseNumber(), is(5));
        assertThat("Invalid mapper result", addressApi.toString(),
                is("AddressApi{city='Gliwice', street='Street', houseNumber=5, flatNumber=4, zipCode='44-100'}"));
    }
}
