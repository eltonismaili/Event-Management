package com.example.eventmanagment.configs;

import com.example.eventmanagment.entities.Address;
import com.example.eventmanagment.entities.User;
import com.example.eventmanagment.entities.enums.Role;
import com.example.eventmanagment.repository.AddressRepository;
import com.example.eventmanagment.repository.UserRepository;
import com.example.eventmanagment.security.AppUserDetailsService;
import com.example.eventmanagment.security.JwtAuthenticationFilter;
import com.example.eventmanagment.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.eventmanagment.entities.enums.Permission.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JwtAuthenticationFilter(authenticationService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers("/api/v1/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/addresses/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/addresses").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/addresses/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/addresses/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/events/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/events").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/events/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/events/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.GET, "/api/v1/venues/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/venues").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/venues/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/venues/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/categories").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/categories/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/tickets").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/tickets/**").hasAnyAuthority(ADMIN_WRITE.getPermission())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/tickets/**").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository,
                                                 AddressRepository addressRepository) {
        var user = new AppUserDetailsService(userRepository);

        String email = "elton@gmail.com";
        userRepository.findByEmail(email)
                .orElseGet(() -> {
                    // Create or fetch an Address first
                    Address address = Address.builder()
                            .street("123 Main St")
                            .city("Springfield")
                            .zipCode("12345")
                            .country("USA")
                            .build();


                    Address savedAddress = addressRepository.save(address);

                    var newUser = User.builder()
                            .name("Elton")
                            .surname("Silva")
                            .email(email)
                            .password(passwordEncoder().encode("password"))
                            .age(20)
                            .roles(Role.ADMIN)
                            .address(savedAddress)
                            .build();

                    return userRepository.save(newUser);
                });

        return user;
    }

}