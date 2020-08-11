package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/customer/signup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupCustomerResponse> signup(final SignupCustomerRequest signupUserRequest) throws SignUpRestrictedException {
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUuid(UUID.randomUUID().toString());
        customerEntity.setFirstName(signupUserRequest.getFirstName());
        customerEntity.setLastName(signupUserRequest.getLastName());
        customerEntity.setEmail(signupUserRequest.getEmailAddress());
        customerEntity.setPassword(signupUserRequest.getPassword());
        customerEntity.setContactNumber(signupUserRequest.getContactNumber());
        CustomerEntity createdCustomerEntity = customerService.saveCustomer(customerEntity);
        SignupCustomerResponse customerResponse = new SignupCustomerResponse().id(createdCustomerEntity.getUuid())
                .status("CUSTOMER SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SignupCustomerResponse>(customerResponse, HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/customer/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestHeader("authorization") final String authorization) throws AuthenticationFailedException {


        if (authorization == null || !authorization.startsWith("Basic ")) {
            throw new AuthenticationFailedException("ATH-003", "Incorrect format of decoded customer name and password");
        }
        String credentials = null;
        try {
            byte[] decode = Base64.getDecoder().decode(authorization.split("Basic ")[1]);
            credentials = new String(decode);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationFailedException("ATH-003", "Incorrect format of decoded customer name and password");
        }

        if (credentials == null || !credentials.matches("(.+?):(.+)")) {
            throw new AuthenticationFailedException("ATH-003", "Incorrect format of decoded customer name and password");
        }

        String[] credentialArray = credentials.split(":");

        CustomerAuthEntity authEntity = customerService.authenticate(credentialArray[0], credentialArray[1]);
        CustomerEntity customer = authEntity.getCustomer();

        LoginResponse loginResponse = new LoginResponse()
                .id(customer.getUuid())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .emailAddress(customer.getEmail())
                .contactNumber(customer.getContactNumber())
                .message("LOGGED IN SUCCESSFULLY");

        HttpHeaders headers = new HttpHeaders();
        headers.add("access-token", authEntity.getAccessToken());
        return new ResponseEntity<>(loginResponse, headers, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/customer/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LogoutResponse> logout(@RequestHeader("authorization") final String authorization) throws AuthorizationFailedException {

        String bearerToken = null;
        LogoutResponse logoutResponse = null;

        try {
            bearerToken = authorization.split("Bearer ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            bearerToken = authorization;
        }

        CustomerAuthEntity customerAuthEntity = customerService.logout(bearerToken);
        if (customerAuthEntity != null) {
            logoutResponse = new LogoutResponse().id(customerAuthEntity.getCustomer().getUuid()).message("LOGGED OUT SUCCESSFULLY");
        }
        return new ResponseEntity<LogoutResponse>(logoutResponse, HttpStatus.OK);
    }
}
