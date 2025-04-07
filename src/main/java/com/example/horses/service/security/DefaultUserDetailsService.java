package com.example.horses.service.security;

import com.example.horses.api.dto.security.PermissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DefaultUserDetailsService implements UserDetailsService {

    private static final List<PermissionType> PERMISSIONS = List.of(PermissionType.HORSE_READ);

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Map<String, String> USERS_DB = Map.of(
            "oleg.k", "P@ssword1234",
            "mahomed.a", "P@ssword1234"
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!USERS_DB.containsKey(username)) {
            return null;
        }

        String password = USERS_DB.get(username);
        String passwordEncoded = passwordEncoder.encode(password);
        Set<SimpleGrantedAuthority> authorities = PERMISSIONS
                .stream()
                .filter(Objects::nonNull)
                .distinct()
                .map(PermissionType::toSimpleGrantedAuthority)
                .collect(Collectors.toSet());

        return new DefaultUserDetails()
                .setUsername(username)
                .setPassword(passwordEncoded)
                .setAuthorities(authorities);
    }

}
