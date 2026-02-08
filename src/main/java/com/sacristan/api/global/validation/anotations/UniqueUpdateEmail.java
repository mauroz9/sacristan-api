package com.sacristan.api.global.validation.anotations;


import com.sacristan.api.global.validation.validators.UniqueUpdateEmailValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUpdateEmailValidator.class)
public @interface UniqueUpdateEmail {

    String message() default "{validation.unique.email}";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};

}
