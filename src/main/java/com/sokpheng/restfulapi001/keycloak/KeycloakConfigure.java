package com.sokpheng.restfulapi001.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KeycloakConfigure {
    @Bean
    public Keycloak keycloakInstance(){
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/")
                .realm("springboot")// your realm you're working on
                .clientId("spring-boot")
                .clientSecret("LefejYYcNWaXAc8asmUaEXV92e7OYkad")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }
}
