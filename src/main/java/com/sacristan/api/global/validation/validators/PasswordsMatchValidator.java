package com.sacristan.api.global.validation.validators;

import com.sacristan.api.global.validation.anotations.PasswordsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.StringUtils;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, Object> {

    private String passwordField;
    private String verifyPasswordField;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        this.passwordField = constraintAnnotation.passwordField();
        this.verifyPasswordField = constraintAnnotation.verifyPasswordField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        String passwordValue = (String) PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(passwordField);
        String verifyPasswordValue = (String) PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(verifyPasswordField);

        return StringUtils.hasText(passwordValue) && passwordField.equals(verifyPasswordValue);
    }
}
