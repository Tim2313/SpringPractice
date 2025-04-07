package com.example.horses.api.http.rest.controller;

import com.example.horses.api.dto.TokenVO;
import com.example.horses.api.dto.security.PermissionType;
import com.example.horses.service.security.DefaultUserDetails;
import com.example.horses.service.security.JwtTokenPayload;
import com.example.horses.service.security.JwtTokenProvider;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/v1/token")
public class TokenRestController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/")
    public TokenVO create() {
        DefaultUserDetails userDetails = (DefaultUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Set<PermissionType> authorities = CollectionUtils.emptyIfNull(userDetails.getAuthorities())
                .stream()
                .map(PermissionType::parse)
                .collect(Collectors.toSet());

        JwtTokenPayload jwtTokenPayload = new JwtTokenPayload()
                .setSubject(userDetails.getUsername())
                .setAuthorities(authorities);

        String token = jwtTokenProvider.createToken(jwtTokenPayload);
        return new TokenVO().setValue(token);
    }

}
