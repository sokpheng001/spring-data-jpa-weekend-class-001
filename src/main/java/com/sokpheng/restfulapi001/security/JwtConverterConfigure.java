package com.sokpheng.restfulapi001.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Configuration
public class JwtConverterConfigure implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final Logger log = LoggerFactory.getLogger(JwtConverterConfigure.class);
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        //
        Collection<GrantedAuthority> roles = new HashSet<>(extractRoles(jwt));
        return new JwtAuthenticationToken(jwt,roles, null);
    }
    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt){
        Map<String, Object> resourceAccess;
        Map<String, Object> realmAccess;
        List<String> roles;
        //
        resourceAccess = jwt.getClaim("resource_access");
        realmAccess = (Map<String, Object>) resourceAccess.get("spring-boot");
        roles = (List<String>) realmAccess.get("roles");
        //log.info("ROLES: {}", roles);// will log (print out on the console)
        return roles.stream()
                .map(role->new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}
