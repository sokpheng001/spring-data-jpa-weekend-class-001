package com.sokpheng.restfulapi001.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    throws Exception{
        //
        // security config here coding
        httpSecurity
                // disable cross site origin
                .cors(AbstractHttpConfigurer::disable)
                // disable cross site request forgery
                .csrf(AbstractHttpConfigurer::disable)
                // the place for allow or deny the request to any specific api endpoint
                .authorizeHttpRequests(
                        // lambda expression syntax
                        re->re
                                /*   /api/v1/customer/123 -> allow only but only the GET http method
                                     /api/v1/customer/pagination
                                     /api/v1/customer?delete=123
                                     /api/v1/customer?id=123&name=jame
                                */
                                .requestMatchers(HttpMethod.GET,"/api/v1/customers/**").permitAll()
                                .anyRequest().authenticated()
                                // allow all requests without authentication
//                                .anyRequest().permitAll()
                                /* deny all of the requests meaning need to authenticate first
                                before access the api
                                 */
//                                .anyRequest().authenticated()

                );
        return httpSecurity.build();
    }
}
