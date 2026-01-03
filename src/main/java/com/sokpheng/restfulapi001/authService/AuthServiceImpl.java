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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final Keycloak keycloak;
    private final CustomerRepository customerRepository;
    private final CustomerMapstruct customerMapstruct;
    public CustomerResponseDto registerUser(CreateCustomerDto createCustomerDto){
        // create user inside the keycloak
        UserRepresentation user = new UserRepresentation();
        user.setFirstName(createCustomerDto.customerName());
        user.setLastName(createCustomerDto.customerName());
        user.setEnabled(true);
        user.setEmail(createCustomerDto.email());
        // set use credential
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setValue(createCustomerDto.password());
        // save credential to user
        user.setCredentials(List.of(credential));
        //
        try(Response response = keycloak.realm("springboot").users().create(user)){
            if(response.getStatus()== HttpStatus.SC_CREATED){
                Customer customer = new Customer();
                customer.setUuid(user.getId());
                customer.setEmail(user.getEmail());
                customer.setCustomerName(user.getFirstName());
                // we don;t need to save the password in the database of Spring Boot, because we have the keycloak
                customer.setIsDeleted(false);
                // save customer to spring boot database
                customerRepository.save(customer);
                return customerMapstruct.mapFromCustomerToCustomerResponseDto(
                        customer
                );
            }
        }catch (Exception exception){
            throw new CustomerException(exception.getMessage());
        }
        return null;
    }
}
