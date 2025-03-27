package com.cartservice.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Value("${cors.allowed-origins:http://localhost:3000}")
    private String allowedOrigins;

    @Value("${cors.allowed-methods:GET,POST,PUT,DELETE,OPTIONS}")
    private String allowedMethods;

    @Value("${cors.allowed-headers:*}")
    private String allowedHeaders;

    @Value("${cors.allow-credentials:true}")
    private boolean allowCredentials;

    @Value("${cors.max-age:3600}")
    private long maxAge;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Set allowed origins from properties
        String[] origins = allowedOrigins.split(",");
        config.setAllowedOrigins(Arrays.asList(origins));
        
        // Set allowed methods from properties
        String[] methods = allowedMethods.split(",");
        config.setAllowedMethods(Arrays.asList(methods));
        
        // Set allowed headers from properties
        String[] headers = allowedHeaders.split(",");
        config.setAllowedHeaders(Arrays.asList(headers));
        
        // Additional CORS settings
        config.setAllowCredentials(allowCredentials);
        config.setMaxAge(maxAge);
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
} 