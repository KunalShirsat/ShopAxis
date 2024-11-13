package com.userService.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/login").permitAll() // Allow login
                        .requestMatchers("/api/v1/register").permitAll() // Allow login
//
                                .requestMatchers("/api/user/address").permitAll() // Allow login
//
                                .requestMatchers("3000/your-addresses").permitAll() // Allow login

.requestMatchers("api/user/getaddress").permitAll()
                           .anyRequest().authenticated() // All other requests need authentication
                );

        return http.build();
    }
}
