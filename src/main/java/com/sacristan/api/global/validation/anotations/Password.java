package com.sacristan.api.global.validation.anotations;

import com.sacristan.api.global.validation.validators.PasswordValidator;
import com.sacristan.api.global.validation.validators.PasswordsMatchValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(
        RetentionPolicy.RUNTIME
)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {

    String message() default "{validation.invalid.password}";
    Class<?>[] groups() default {};
    Class<? extends jakarta.validation.Payload>[] payload() default {};

}
