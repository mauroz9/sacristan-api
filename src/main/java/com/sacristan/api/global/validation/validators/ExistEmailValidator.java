package com.sacristan.api.global.validation.validators;

import com.sacristan.api.global.entities.users.user.UserRepository;
import com.sacristan.api.global.validation.anotations.ExistEmail;
import com.sacristan.api.global.validation.anotations.UniqueUpdateEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public class ExistEmailValidator implements ConstraintValidator<ExistEmail, String> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsByEmail(s);
    }
}
