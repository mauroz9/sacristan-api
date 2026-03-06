package com.sacristan.api.global.validation.validators;

import com.sacristan.api.global.repositories.UserRepository;
import com.sacristan.api.global.validation.anotations.UniqueUpdateEmail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public class UniqueUpdateEmailValidator implements ConstraintValidator<UniqueUpdateEmail, String> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String idStr = pathVariables.get("id");

        Long id = idStr != null ? Long.parseLong(idStr) : null;

        return id != null
                ? !repository.existsByEmailAndIdNot(s,id)
                : !repository.existsByEmailIgnoreCase(s);
    }
}
