package com.example.horses.service.security;

import com.example.horses.api.dto.security.PermissionType;
import com.example.horses.domain.entity.User;
import com.example.horses.excepition.EntityNotFoundException;
import com.example.horses.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DefaultUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserDetailsService.class);

    private static final List<PermissionType> PERMISSIONS = List.of(PermissionType.HORSE_READ);

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user;
        try {
            user = userService.findByEmail(email);
        } catch (EntityNotFoundException ex) {
            LOG.warn(ex.getMessage());
            return null;
        }

        Set<SimpleGrantedAuthority> authorities = PERMISSIONS
                .stream()
                .filter(Objects::nonNull)
                .distinct()
                .map(PermissionType::toSimpleGrantedAuthority)
                .collect(Collectors.toSet());

        return new DefaultUserDetails()
                .setUsername(user.getEmail())
                .setPassword(user.getPassword())
                .setAuthorities(authorities);
    }
}
