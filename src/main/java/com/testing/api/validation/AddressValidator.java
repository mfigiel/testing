package com.testing.api.validation;

import com.testing.api.resource.PersonAddressApi;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<AddressValidation, PersonAddressApi> {

    @Override
    public boolean isValid(PersonAddressApi personAddressApi, ConstraintValidatorContext constraintValidatorContext) {
        PersonAddressApi address = personAddressApi;
        return isNotNullAndNotEmpty(address.getCity()) && isNotNullAndNotEmpty(address.getStreet()) &&
                isNotNullAndNotEmpty(address.getZipCode());
    }

    private boolean isNotNullAndNotEmpty(String field) {
        return field != null && !field.isEmpty();
    }

    void buildConstraint(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context
                .buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
