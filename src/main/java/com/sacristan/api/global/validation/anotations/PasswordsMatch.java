package com.sacristan.api.global.validation.anotations;

import com.sacristan.api.global.validation.validators.PasswordsMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(
        RetentionPolicy.RUNTIME
)
@Constraint(validatedBy = PasswordsMatchValidator.class)
@Documented
public @interface PasswordsMatch {

        String message() default "{validation.passwords.match}";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};

        String passwordField();
        String verifyPasswordField();

        @Target(ElementType.TYPE)
        @Retention(RetentionPolicy.RUNTIME)
        @interface List {
            PasswordsMatch[] value();
        }

}
