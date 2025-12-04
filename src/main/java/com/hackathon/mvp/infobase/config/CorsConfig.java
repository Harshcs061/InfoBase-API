package com.hackathon.mvp.infobase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig {

    @Value("#{'${app.cors.allowed-origins:*}'.split(',')}")
    private List<String> allowedOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                var mapping = registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("Authorization", "Link")
                        .maxAge(3600);

                // If you configured specific allowed origins, use them; otherwise allow all patterns
                if (allowedOrigins != null && !allowedOrigins.isEmpty() && !allowedOrigins.get(0).equals("*")) {
                    mapping.allowedOrigins(allowedOrigins.toArray(new String[0]));
                } else {
                    mapping.allowedOriginPatterns("*").allowCredentials(true);
                }
            }
        };
    }
}