package com.example.horses.api.dto.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;

public enum PermissionType {
    HORSE_READ;

    public static SimpleGrantedAuthority toSimpleGrantedAuthority(PermissionType permissionType) {
        return Optional.ofNullable(permissionType)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .orElseThrow(IllegalArgumentException::new);
    }

    public static PermissionType parse(GrantedAuthority grantedAuthority) {
        return Optional.ofNullable(grantedAuthority)
                .map(GrantedAuthority::getAuthority)
                .map(PermissionType::valueOf)
                .orElseThrow(IllegalArgumentException::new);
    }
}
