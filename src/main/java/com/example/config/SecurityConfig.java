package com.example.config; // change to match your actual package

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // ❗ temporarily disable CSRF
            .authorizeHttpRequests()
                .anyRequest().permitAll();
        return http.build();
    }
}
