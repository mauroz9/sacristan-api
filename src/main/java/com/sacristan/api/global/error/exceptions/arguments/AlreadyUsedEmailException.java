package com.sacristan.api.global.error.exceptions.arguments;


import lombok.Getter;

@Getter
public class AlreadyUsedEmailException extends RuntimeException {

    private String email;

    public AlreadyUsedEmailException(String message, String email) {
        super(message);
        this.email = email;
    }
}
