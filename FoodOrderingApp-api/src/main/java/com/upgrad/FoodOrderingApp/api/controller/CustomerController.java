package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.*;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.*;
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

    /**
     * Method for customer signup
     * @return returns the “uuid” of the registered customer
     * @throws SignUpRestrictedException
     */

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.POST, path = "/customer/signup",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupCustomerResponse> signup(@RequestBody(required = false) final SignupCustomerRequest signupUserRequest) throws SignUpRestrictedException {
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUuid(UUID.randomUUID().toString());
        customerEntity.setFirstName(signupUserRequest.getFirstName());
        customerEntity.setLastName(signupUserRequest.getLastName());
        customerEntity.setEmail(signupUserRequest.getEmailAddress());
        customerEntity.setPassword(signupUserRequest.getPassword());
        customerEntity.setContactNumber(signupUserRequest.getContactNumber());
        validateSignup(customerEntity);
        CustomerEntity createdCustomerEntity = customerService.saveCustomer(customerEntity);
        SignupCustomerResponse customerResponse = new SignupCustomerResponse().id(createdCustomerEntity.getUuid())
                .status("CUSTOMER SUCCESSFULLY REGISTERED");
        return new ResponseEntity<SignupCustomerResponse>(customerResponse, HttpStatus.CREATED);
    }

    //method to validate signup of a customer
    private void validateSignup(CustomerEntity customerEntity) throws SignUpRestrictedException {
        String firstName = customerEntity.getFirstName();
        String email = customerEntity.getEmail();
        String contactNumber = customerEntity.getContactNumber();
        String password = customerEntity.getPassword();
        if (firstName == null || firstName.isEmpty()
                || email == null || email.isEmpty()
                || contactNumber == null || contactNumber.isEmpty()
                || password == null || password.isEmpty()) {
            throw new SignUpRestrictedException("SGR-00" +
                    "5", "Except last name all fields should be filled");
        }
    }

    /**
     * Method for customer login
     * @param authorization
     * @return returns the uuid, first name, last name, contact number, and email address
     * of the authenticated customer and access token in response header
     * @throws AuthenticationFailedException
     */

    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/customer/login",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    /**
     * Method for customer logout
     * @param authorization
     * @return returns the 'uuid' of the signed out customer
     * @throws AuthorizationFailedException
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST,
            path = "/customer/logout",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    /**
     * Method for updating customer details
     * @param authorization
     * @param updateCustomerRequest
     * @return returns  uuid,first name and last name of the customer
     * @throws AuthorizationFailedException
     * @throws UpdateCustomerException
     */
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/customer",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UpdateCustomerResponse> updateCustomerDetails(@RequestHeader("authorization") final String authorization,
                                                                        @RequestBody(required = false)
                                                                                UpdateCustomerRequest updateCustomerRequest)
            throws AuthorizationFailedException, UpdateCustomerException {
        if (updateCustomerRequest.getFirstName() == null || updateCustomerRequest.getFirstName() == "") {
            throw new UpdateCustomerException("UCR-002", "First name field should not be empty");
        }
        String bearerToken = authorization.split("Bearer ")[1];
        CustomerEntity updatedCustomerEntity = customerService.getCustomer(bearerToken);
        updatedCustomerEntity.setFirstName(updateCustomerRequest.getFirstName());
        updatedCustomerEntity.setLastName(updateCustomerRequest.getLastName());
        CustomerEntity newUpdatedCustomerEntity = customerService.updateCustomer(updatedCustomerEntity);
        UpdateCustomerResponse updateCustomerResponse = new UpdateCustomerResponse().firstName(newUpdatedCustomerEntity.getFirstName()).lastName(newUpdatedCustomerEntity.getLastName()).id(newUpdatedCustomerEntity.getUuid()).status("CUSTOMER DETAILS UPDATED SUCCESSFULLY");
        return new ResponseEntity<UpdateCustomerResponse>(updateCustomerResponse, HttpStatus.OK);
    }

    //method to validate password
    private void validatePassword(UpdatePasswordRequest updatePasswordRequest) throws UpdateCustomerException {
        String oldPassword = updatePasswordRequest.getOldPassword();
        String newPassword = updatePasswordRequest.getNewPassword();
        if (oldPassword == null
                || oldPassword.isEmpty()
                || newPassword == null
                || newPassword.isEmpty()) {
            throw new UpdateCustomerException("UCR-003", "No field should be empty");
        }
    }

    /**
     * method for updating customer password
     * @param authorization
     * @param updatePasswordRequest
     * @return returns uuid of the customer
     * @throws AuthorizationFailedException
     * @throws UpdateCustomerException
     */
    @CrossOrigin
    @RequestMapping(
            method = RequestMethod.PUT, path = "/customer/password",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UpdatePasswordResponse> updateCustomerPassword(@RequestHeader("authorization") final String authorization,
                                                                         @RequestBody(required = false) UpdatePasswordRequest updatePasswordRequest)
            throws AuthorizationFailedException, UpdateCustomerException {

        validatePassword(updatePasswordRequest);

        String accessToken = authorization.split("Bearer ")[1];
        String oldPassword = updatePasswordRequest.getOldPassword();
        String newPassword = updatePasswordRequest.getNewPassword();

        CustomerEntity updateCustomerEntity = customerService.getCustomer(accessToken);
        CustomerEntity updatedCustomerEntity = customerService.updateCustomerPassword(oldPassword, newPassword, updateCustomerEntity);
        UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse().id(updatedCustomerEntity.getUuid()).status("CUSTOMER PASSWORD UPDATED SUCCESSFULLY");
        return new ResponseEntity<UpdatePasswordResponse>(updatePasswordResponse, HttpStatus.OK);

    }
}
