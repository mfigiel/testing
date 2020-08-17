package com.gateway.api.validation;

import com.gateway.api.resource.OrderApi;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderValidator implements ConstraintValidator<OrderValidation, OrderApi> {

    @Override
    public boolean isValid(OrderApi order, ConstraintValidatorContext constraintValidatorContext) {
        return order != null && order.getProducts() != null
                && !order.getProducts().isEmpty();
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
