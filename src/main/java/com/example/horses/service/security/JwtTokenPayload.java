package com.example.horses.service.security;

import com.example.horses.api.dto.security.PermissionType;

import java.util.Set;

public class JwtTokenPayload {

    private String subject;

    private Set<PermissionType> authorities;

    public String getSubject() {
        return subject;
    }

    public JwtTokenPayload setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Set<PermissionType> getAuthorities() {
        return authorities;
    }

    public JwtTokenPayload setAuthorities(Set<PermissionType> authorities) {
        this.authorities = authorities;
        return this;
    }
}
