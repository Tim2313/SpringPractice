package com.example.horses.service.security;

import com.example.horses.api.dto.security.PermissionType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtTokenProvider {

    private static final Logger LOG = LoggerFactory.getLogger(JwtTokenProvider.class);

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORITIES_CLAIM = "authorities";

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    private SecretKey secretKey;

    private JwtParser jwtParser;

    @PostConstruct
    private void init() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    public String createToken(JwtTokenPayload jwtTokenPayload) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String subject = Optional.ofNullable(jwtTokenPayload)
                .map(JwtTokenPayload::getSubject)
                .orElse(StringUtils.EMPTY);

        Set<PermissionType> authorities = Optional.ofNullable(jwtTokenPayload)
                .map(JwtTokenPayload::getAuthorities)
                .orElse(new HashSet<>());

        String token = Jwts.builder()
                .issuedAt(now)
                .expiration(validity)
                .subject(subject)
                .claim(AUTHORITIES_CLAIM, authorities)
                .signWith(secretKey)
                .compact();

        return BEARER_TOKEN_PREFIX + token;
    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(token);
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    public boolean isValid(String token) {
        Claims claims;
        try {
            claims = jwtParser.parseSignedClaims(token).getPayload();
        } catch (JwtException | IllegalArgumentException ex) {
            LOG.info("JWT token is invalid");
            return false;
        }

        return !claims.getExpiration().before(new Date());
    }


    private String getUsername(String token) {
        return jwtParser.parseSignedClaims(token).getPayload().getSubject();
    }

    private List<SimpleGrantedAuthority> getAuthorities(String token) {
        Object authoritiesRaw = jwtParser.parseSignedClaims(token).getPayload().get(AUTHORITIES_CLAIM);
        if (authoritiesRaw == null) {
            return Collections.emptyList();
        }
        List<String> rawPermissionTypeList;
        try {
            rawPermissionTypeList = (List<String>) jwtParser.parseSignedClaims(token)
                    .getPayload()
                    .get(AUTHORITIES_CLAIM);
        } catch (ClassCastException ex) {
            return Collections.emptyList();
        }

        return ListUtils.emptyIfNull(rawPermissionTypeList)
                .stream()
                .map(PermissionType::valueOf)
                .map(PermissionType::toSimpleGrantedAuthority)
                .toList();
    }

}
