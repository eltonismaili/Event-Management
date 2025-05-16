package com.example.eventmanagment.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/v1/users/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").permitAll() // Add .GET)
                .requestMatchers(HttpMethod.GET, "/api/v1/addresses/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/addresses").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/addresses/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/addresses/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/roles/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/roles").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/roles/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/roles/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/events/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/events").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/events/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/events/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/venues/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/venues").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/venues/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/venues/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/categories").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/categories/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/reviews/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/tickets/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/tickets").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/tickets/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/v1/tickets/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // Basic Authentication

        return http.build();
    }
}