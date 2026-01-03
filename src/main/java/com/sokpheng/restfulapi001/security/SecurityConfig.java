package com.sokpheng.restfulapi001.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtConverterConfigure jwtConverterConfigure;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    throws Exception{
        httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                // this for authentication and authorization
                .authorizeHttpRequests(
                        re->re
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/customers/**")
                                .hasAnyRole("admin","super_admin")
                );
        // integrate with authorization server (keycloak)
//        httpSecurity.oauth2ResourceServer(jwt->jwt.jwt(Customizer.withDefaults()));
        httpSecurity.oauth2ResourceServer(jwt->jwt.jwt(
                convert->convert.jwtAuthenticationConverter(jwtConverterConfigure))
        );
        return httpSecurity.build();
    }
}
