//package com.sokpheng.restfulapi001.security;
//
//import com.sokpheng.restfulapi001.exception.CustomerException;
//import com.sokpheng.restfulapi001.mapper.CustomerMapstruct;
//import com.sokpheng.restfulapi001.model.dto.CreateCustomerDto;
//import com.sokpheng.restfulapi001.model.dto.CustomerResponseDto;
//import com.sokpheng.restfulapi001.model.entities.Customer;
//import com.sokpheng.restfulapi001.model.repository.CustomerRepository;
//import jakarta.ws.rs.core.Response;
//import lombok.RequiredArgsConstructor;
//import org.apache.http.HttpStatus;
//import org.keycloak.OAuth2Constants;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.security.core.parameters.P;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    private final Keycloak keycloak;
//    private final CustomerMapstruct mapstruct;
//    private final CustomerRepository customerRepository;
//    public CustomerResponseDto register(CreateCustomerDto u){
//        UserRepresentation user = new UserRepresentation();
//        user.setUsername(u.customerName());
//        user.setEmail(u.email());
//        user.setEmailVerified(true);
//        user.setEnabled(true);
//
//        //set password
//        CredentialRepresentation credential = new CredentialRepresentation();
//        credential.setType(OAuth2Constants.PASSWORD);
//        credential.setValue(u.password());
//        credential.setTemporary(false);
//
//        // set password
//        user.setCredentials(List.of(credential));
//        try(Response r = keycloak.realm("springboot").users().create(user)){
//            System.out.println(r.getStatus());
//            if(r.getStatus()== HttpStatus.SC_CREATED){
//                Customer customer =  mapstruct.fromRequestToCustomer(
//                        u
//                );
//                customer.setIsDeleted(false);
//                customer.setUuid(user.getId());
//                // save to database also
//                customerRepository.save(customer);
//                return mapstruct.mapFromCustomerToCustomerResponseDto(
//                       customer
//                );
//            }
//        }catch (Exception exception){
//            throw new CustomerException(exception.getMessage());
//        }
//        return null;
//    }
//}
