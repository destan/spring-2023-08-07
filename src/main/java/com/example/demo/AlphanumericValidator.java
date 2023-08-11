package com.example.demo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class AlphanumericValidator implements ConstraintValidator<Alphanumeric, String> {

    private static final Pattern ALPHANUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (ALPHANUMERIC_PATTERN.matcher(value).matches()) {
            return true;
        }

        return false;
    }
}
