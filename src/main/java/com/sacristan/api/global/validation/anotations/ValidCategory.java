package com.sacristan.api.global.validation.anotations;

import com.sacristan.api.global.validation.validators.ValidCategoryValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCategoryValidator.class)
@Documented
public @interface ValidCategory {

    String message() default "{validation.invalid.category}";
    Class<?>[] groups() default {};
    Class<? extends jakarta.validation.Payload>[] payload() default {};

}

