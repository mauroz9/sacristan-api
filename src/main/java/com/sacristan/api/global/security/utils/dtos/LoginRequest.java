package com.sacristan.api.global.security.utils.dtos;

public record LoginRequest(
        String email,
        String password
) {}
