package com.sacristan.api.global.security.config.jwt.refresh;

public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException(String message) {
        super(message);
    }
}
