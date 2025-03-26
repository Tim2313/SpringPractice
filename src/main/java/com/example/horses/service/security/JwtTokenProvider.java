package com.example.horses.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    public String createToken() {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());

        String token = Jwts.builder()
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey)
                .compact();

        return BEARER_TOKEN_PREFIX + token;
    }
}
