package com.sokpheng.restfulapi001.authService;

import com.sokpheng.restfulapi001.exception.CustomerException;
import com.sokpheng.restfulapi001.mapper.CustomerMapstruct;
import com.sokpheng.restfulapi001.model.dto.CreateCustomerDto;
import com.sokpheng.restfulapi001.model.dto.CustomerResponseDto;
import com.sokpheng.restfulapi001.model.entities.Customer;
import com.sokpheng.restfulapi001.model.repository.CustomerRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImplV1 {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImplV1.class);
    private final Keycloak keycloak;
    private final CustomerRepository customerRepository;
    private final CustomerMapstruct customerMapstruct;
    private final ThreadPoolTaskExecutorBuilder threadPoolTaskExecutorBuilder;

    public CustomerResponseDto registerUser(CreateCustomerDto createCustomerDto){
        // create user inside the keycloak
        UserRepresentation user = new UserRepresentation();
        user.setUsername(createCustomerDto.customerName());
        user.setFirstName(createCustomerDto.customerName());
        user.setLastName(createCustomerDto.customerName());
        user.setEnabled(true);
        user.setEmail(createCustomerDto.email());
        // set use credential
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setValue(createCustomerDto.password());
        credential.setType(CredentialRepresentation.PASSWORD);
        // save credential to user
        user.setCredentials(List.of(credential));
        //
        Response response0;
        try(Response response = keycloak.realm("springboot").users().create(user)){
            response0 = response;
            log.info("HTTP Status: {}",response.getStatus());
            if(response.getStatus() == HttpStatus.SC_CREATED){
                Customer customer = new Customer();
                customer.setUuid(getKeycloakUserId(response));
                customer.setEmail(user.getEmail());
                customer.setCustomerName(user.getFirstName());
                // we don;t need to save the password in the database of Spring Boot, because we have the keycloak
                customer.setIsDeleted(false);
                // save customer to spring boot database
                customerRepository.save(customer);
                // send email to verify account
                sendEmail(keycloak,user,getKeycloakUserId(response),"springboot",List.of("VERIFY_EMAIL"));
                // map user for response
                return customerMapstruct.mapFromCustomerToCustomerResponseDto(
                        customer
                );
            }
        }catch (Exception exception){
            throw new CustomerException(exception.getMessage());
        }
        throw new RuntimeException(String.valueOf(response0.getStatus()));
    }
    public String resetPassword(Authentication authentication, ResetPasswordDto resetPasswordDto){
        // in this case we don't need to validate is the token is expired
        // , because we have configured the security in Spring boot
        Jwt jwt = (Jwt) authentication.getPrincipal(); // get jwt object
        String userId = jwt.getSubject();// id of the user in keycloak
        String userEmail = String.valueOf(Optional.ofNullable(jwt.getClaim("email")));
        UserRepresentation user = keycloak.realm("springboot")
                .users().get(userId).toRepresentation(); // get user by id - uuid
        if(user!=null){
            if(Objects.equals(user.getEmail(), resetPasswordDto.email())){
                // create new password for user in keycloak
                CredentialRepresentation credential = new CredentialRepresentation();
                credential.setValue(resetPasswordDto.newPassword());
                // reset password
                keycloak.realm("springboot")
                        .users().get(userId)
                        .resetPassword(credential);
            }else {
                throw new CustomerException("Email is invalid");
            }

        }
        assert user != null;
        return user.getId();
    }
    private void sendEmail(Keycloak keycloak, UserRepresentation user, String userId,String realm,List<String> emailActions){
        List<String>actions  = List.of("VERIFY_EMAIL");
        user.setRequiredActions(actions);
        keycloak.realm(realm).users().get(userId)
                .update(user);
        keycloak.realm(realm).users().get(userId)
                .executeActionsEmail(actions);
    }
    private String getKeycloakUserId(Response response){
        return response.getLocation()
                .getPath()
                .replaceAll(".*/([^/]+)$", "$1");

    }
}
