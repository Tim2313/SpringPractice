package com.example.horses.conf.http;

import com.example.horses.conf.http.filter.DefaultBasicAuthenticationFilter;
import com.example.horses.conf.http.filter.JwtAuthenticationFilter;
import com.example.horses.service.security.DefaultDaoAuthenticationProvider;
import com.example.horses.service.security.DefaultUserDetailsService;
import com.example.horses.service.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurer {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            DefaultBasicAuthenticationFilter defaultBasicAuthenticationFilter,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationManager authenticationManager
    ) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(
                        defaultBasicAuthenticationFilter,
                        AnonymousAuthenticationFilter.class
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        DefaultBasicAuthenticationFilter.class
                )
                .sessionManagement(config -> config.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests(config -> config.anyRequest().authenticated())
                .authenticationManager(authenticationManager)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(DefaultUserDetailsService defaultUserDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DefaultDaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(defaultUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultBasicAuthenticationFilter defaultBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new DefaultBasicAuthenticationFilter(authenticationManager);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        return new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider);
    }

}
