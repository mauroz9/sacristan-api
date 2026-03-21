package com.sacristan.api.global.validation.validators;

import com.sacristan.api.global.validation.anotations.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s.length() < 8)
            return true;

        return false;
    }
}
