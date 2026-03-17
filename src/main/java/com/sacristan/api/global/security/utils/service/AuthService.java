package com.sacristan.api.global.security.utils.service;

import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.security.config.jwt.access.JwtProvider;
import com.sacristan.api.global.security.config.jwt.refresh.RefreshToken;
import com.sacristan.api.global.security.config.jwt.refresh.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public String  generateToken(String email, String password) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtProvider.generateToken(auth);
    }

    public RefreshToken generateRefreshToken(User user) {
        refreshTokenService.deleteByUser(user);
        return refreshTokenService.createRefreshToken(user);

    }

}
