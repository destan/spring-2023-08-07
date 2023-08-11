package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfileValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Profile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (target instanceof Profile profile) {
            Integer age = profile.getAge();
            String photo = profile.getPhoto();

            if (age > 18 && (photo == null || photo.isBlank())) {
                var errorArgs = new String[]{String.valueOf(age)};
                errors.rejectValue("photo", "profile.url_should_exists_when_age_18", errorArgs, "photo should exists if age 18+ (default)");
            }
        }
    }
}
