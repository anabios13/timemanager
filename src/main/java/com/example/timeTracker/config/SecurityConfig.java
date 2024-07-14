package com.example.timeTracker.config;


import com.example.timeTracker.filters.JWTFilter;
import com.example.timeTracker.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsService userDetailsService;


    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JWTFilter jwtFilter) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        //    .anyRequest().authenticated());
                        .requestMatchers("/api/users/**", "/api/projects/**").hasRole("ADMIN")
                        .requestMatchers("/api/records/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/auth/login", "/auth/registration").permitAll());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(new CustomAuthenticationProvider(userDetailsService, getPasswordEncoder())));
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new JWTTokenEncoderProxy(new BCryptPasswordEncoder());
    }
}
