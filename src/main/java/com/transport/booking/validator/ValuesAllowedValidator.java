package com.transport.booking.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@Slf4j
public class ValuesAllowedValidator implements ConstraintValidator<ValuesAllowed, Object> {

    private String[] values;
    private String message;

    @Override
    public void initialize(ValuesAllowed constraintAnnotation) {
        values = constraintAnnotation.values();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean isValid = false;
        if (object != null) {
            isValid = Arrays.asList(values).contains(object.toString());
        }
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message.concat(Arrays.toString(values)))
                    .addConstraintViolation();
        }
        return isValid;
    }
}
