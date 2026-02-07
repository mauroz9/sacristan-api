package com.sacristan.api.global.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Builder
public record ApiValidationSubError(

        String object,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String field,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String rejectedValue
) {

    public ApiValidationSubError(String object, String message) {
        this(object, message, null, null);
    }

    public static ApiValidationSubError from(ObjectError error) {
        ApiValidationSubError result = null;

        if (error instanceof FieldError fieldError) {
            result = ApiValidationSubError.builder()
                    .object(error.getObjectName())
                    .message(error.getDefaultMessage())
                    .field(fieldError.getField())
                    .rejectedValue(fieldError.getRejectedValue().toString())
                    .build();
        } else {
            result = ApiValidationSubError.builder()
                    .object(error.getObjectName())
                    .message(error.getDefaultMessage())
                    .build();
        }

        return result;
    }

}
