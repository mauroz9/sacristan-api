package com.sacristan.api.global.validation.anotations;


import com.sacristan.api.global.validation.validators.ExistEmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistEmailValidator.class)
public @interface ExistEmail {

    String message() default "{validation.exist.email}";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};

}
