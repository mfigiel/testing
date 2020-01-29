package com.testing.api.validation;

import com.testing.api.resource.AddressApi;
import com.testing.api.resource.OrderApi;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderValidator implements ConstraintValidator<OrderValidation, OrderApi> {

    @Override
    public boolean isValid(OrderApi order, ConstraintValidatorContext constraintValidatorContext) {
        return order != null && order.getClientId() != null && order.getProducts() != null
                && !order.getProducts().isEmpty() && order.getOrderDate() != null;
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
