package com.example.demo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AlphanumericValidator implements ConstraintValidator<Alphanumeric, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value.contains("'") || value.contains("?")) {
            return false;
        }

        return true;
    }
}
