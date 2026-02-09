package com.sacristan.api.global.validation.validators;

import com.sacristan.api.global.repositories.UserRepository;
import com.sacristan.api.global.validation.anotations.UniqueUpdateUsername;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public class UniqueUpdateUsernameValidator implements ConstraintValidator<UniqueUpdateUsername, String> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpServletRequest request;

    Long id;

    @Override
    public void initialize(UniqueUpdateUsername constraintAnnotation) {
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String idStr = pathVariables.get("id");

        id = idStr != null ? Long.parseLong(idStr) : null;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return id != null
                ? !repository.existsByUsernameIgnoreCaseAndIdNot(s,id)
                : !repository.existsByUsernameIgnoreCase(s);
    }
}
