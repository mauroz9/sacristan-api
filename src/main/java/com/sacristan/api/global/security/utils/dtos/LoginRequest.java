package com.sacristan.api.global.security.utils.dtos;

import com.sacristan.api.global.validation.anotations.ExistEmail;

public record LoginRequest(
        @ExistEmail String email,
        String password
) {}
