package com.sacristan.api.global.security.config.jwt.refresh;

import com.sacristan.api.global.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh.duration}")
    private Long durationInMinutes;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public int deleteByUser(User user) {
        return refreshTokenRepository.deleteByUser(user);
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpirationDate(Instant.now().plusSeconds(durationInMinutes * 60));

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verify(RefreshToken refreshToken) {
        // if caducado
        if (refreshToken.getExpirationDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Expired refresh token " + refreshToken.getToken() + " Please, log in again.");
        }

        return refreshToken;
    }

}
