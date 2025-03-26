package com.example.horses.api.http.rest.controller;

import com.example.horses.api.dto.TokenVO;
import com.example.horses.service.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/token")
public class TokenRestController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/")
    public TokenVO create() {
        String token = jwtTokenProvider.createToken();
        return new TokenVO().setValue(token);
    }

}
