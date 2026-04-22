package com.sacristan.api.global.security.utils.controller;

import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.security.config.jwt.access.JwtProvider;
import com.sacristan.api.global.security.config.jwt.refresh.RefreshToken;
import com.sacristan.api.global.security.config.jwt.refresh.RefreshTokenNotFoundException;
import com.sacristan.api.global.security.config.jwt.refresh.RefreshTokenService;
import com.sacristan.api.global.security.utils.dtos.JwtUserResponse;
import com.sacristan.api.global.security.utils.dtos.LoginRequest;
import com.sacristan.api.global.security.utils.dtos.RefreshTokenRequest;
import com.sacristan.api.global.security.utils.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SecurityController
{

    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;
    private final AuthService authService;

    // DELETE THIS METHOD AFTER TESTING
    @GetMapping("/try")
    public ResponseEntity<String> tryAuth(
            @AuthenticationPrincipal User user
    ) {
        System.out.println("Trying security: " + user);
        return ResponseEntity.ok("It works!");
    }

    @PostMapping("/refresh-token")
    @Transactional
    public ResponseEntity<JwtUserResponse> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequest
    ) {
        String refreshToken = refreshTokenRequest.refreshToken();
        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verify)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtProvider.generateToken(user);
                    refreshTokenService.deleteByUser(user);
                    RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user);
                    return ResponseEntity.ok(
                            JwtUserResponse.of(
                                    user,
                                    token,
                                    newRefreshToken.getToken()
                            )
                    );
                }).orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token not found"));
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<JwtUserResponse> login (
            @RequestBody LoginRequest loginRequest
    ) {

        String token = authService.generateToken(loginRequest.email(), loginRequest.password());
        User user = (User)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String refreshToken = authService.generateRefreshToken(user).getToken();

        JwtUserResponse jwtUserResponse = JwtUserResponse.of(
                user,
                token,
                refreshToken
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtUserResponse);
    }

    @PostMapping("/logout")
    @Transactional
    public ResponseEntity<Map<String,Object>> logout(
            @AuthenticationPrincipal User user
    ) {

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("detail","No user is currently authenticated"));
        }

        refreshTokenService.deleteByUser(user);
        SecurityContextHolder.clearContext();

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("detail", "Logged out successfully"));
    }

}
