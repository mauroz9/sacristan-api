package com.sacristan.api.global.validation.validators;

import com.sacristan.api.global.entities.content.category.CategoryModelService;
import com.sacristan.api.global.validation.anotations.ValidCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidCategoryValidator implements ConstraintValidator<ValidCategory, Long> {

    private final CategoryModelService categoryModelService;

    @Override
    public void initialize(ValidCategory constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long categoryId, ConstraintValidatorContext context) {
        if (categoryId == null) {
            return false;
        }

        try {
            categoryModelService.getById(categoryId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

