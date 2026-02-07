package com.sacristan.api.global.validation.anotations;


import com.sacristan.api.global.validation.validators.UniqueUpdateEmailValidator;
import com.sacristan.api.global.validation.validators.UniqueUpdateUsernameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUpdateUsernameValidator.class)
public @interface UniqueUpdateUsername {

    String message() default "{validation.unique.username}";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};

}
