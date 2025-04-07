package com.example.horses.conf.http.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class DefaultBasicAuthenticationFilter extends BasicAuthenticationFilter {
    public DefaultBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
}
