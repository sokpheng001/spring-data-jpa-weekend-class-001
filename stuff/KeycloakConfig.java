//package com.sokpheng.restfulapi001.security;
//
//import org.keycloak.OAuth2Constants;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class KeycloakConfig {
//    @Value("${keycloak.server-url}")
//    private String keycloakSeverUrl;
//    @Bean
//    public Keycloak keycloakInstance(){
//       return KeycloakBuilder.builder()
//               .serverUrl(keycloakSeverUrl)
//               .realm("springboot")
//               .clientId("test")
//               .clientSecret("OMmlG23ukk2OT9FV6EX8wg3MLfKi1Vn8")
//               .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//               .build();
//    }
//}
