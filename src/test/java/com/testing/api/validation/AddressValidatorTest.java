package com.testing.api.validation;

import com.testing.api.resource.PersonAddressApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(JUnitPlatform.class)
class AddressValidatorTest {

    private ConstraintValidatorContext constraintValidatorContext;

    private AddressValidator validator;

    @BeforeEach
    void init() {
        constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
        validator = Mockito.spy(AddressValidator.class);
        doNothing().when(validator).buildConstraint(any(), anyString());
    }

    @Test
    void testOK_correctData() {
        // for
        PersonAddressApi personAddressApi = new PersonAddressApi();
        personAddressApi.setCity("City");
        personAddressApi.setStreet("Street");
        personAddressApi.setZipCode("44-100");

        // when
        boolean result = validator.isValid(personAddressApi, constraintValidatorContext);

        // then
        assertThat(result,is(true));
    }

    @Test
    void testFalse_cityNull() {
        // for
        PersonAddressApi personAddressApi = new PersonAddressApi();
        personAddressApi.setStreet("Street");
        personAddressApi.setZipCode("44-100");

        // when
        boolean result = validator.isValid(personAddressApi, constraintValidatorContext);

        // then
        assertThat(result,is(false));
    }

    @Test
    void testFalse_allIsEmpty() {
        // for
        PersonAddressApi personAddressApi = new PersonAddressApi();
        personAddressApi.setCity("");
        personAddressApi.setStreet("");
        personAddressApi.setZipCode("");

        // when
        boolean result = validator.isValid(personAddressApi, constraintValidatorContext);

        // then
        assertThat(result,is(false));
    }

    @Test
    void testFalse_allIsNull() {
        // for
        PersonAddressApi personAddressApi = new PersonAddressApi();

        // when
        boolean result = validator.isValid(personAddressApi, constraintValidatorContext);

        // then
        assertThat(result,is(false));
    }
}