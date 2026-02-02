package com.sacristan.api.global.security.config.jwt.access;

import com.sacristan.api.global.error.exceptions.JwtTokenException;
import com.sacristan.api.global.models.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Log
public class JwtProvider {

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.duration}")
    private int jwtLifeDuration;

    private JwtParser jwtParser;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        jwtParser = Jwts.parser()
                .verifyWith(secretKey)
                .build();
    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return generateToken(user);
    }

    public String generateToken(User user) {
        Date tokenExpirationDateTime = Date.from(
                LocalDateTime.now()
                        .plusMinutes(jwtLifeDuration)
                        .atZone(ZoneId.systemDefault())
                        .toInstant());


        return Jwts.builder()
                .header().type(TOKEN_TYPE).and()
                .subject(user.getId().toString())
                .issuedAt(new Date()) // Fecha de creacion
                .expiration(tokenExpirationDateTime)
                .signWith(secretKey)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        return Long.parseLong(jwtParser.parseSignedClaims(token).getPayload().getSubject());
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseSignedClaims(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException |
                 UnsupportedJwtException | IllegalArgumentException ex) {
            log.info("Error con el Token: " + ex.getMessage());
            throw new JwtTokenException(ex.getMessage());
        }
    }


}
