package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CustomerAuthDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerAuthDao customerAuthDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    /**
     * method to save customer details
     *
     * @param customerEntity
     * @return stores customer details in the db
     * @throws SignUpRestrictedException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) throws SignUpRestrictedException {

        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";

        String contactNumber = customerEntity.getContactNumber();
        String email = customerEntity.getEmail();
        String password = customerEntity.getPassword();
        //checks for email-id format
        if (!email.matches(emailRegex)) {
            throw new SignUpRestrictedException("SGR-002", "Invalid email-id format!");
        } else if (!contactNumber.matches("[0-9]+")
                || contactNumber.length() != 10) {
            throw new SignUpRestrictedException("SGR-003", "Invalid contact number!");
        } else if (password.length() < 8
                || !password.matches(".*\\d.*")
                || !password.matches(".*[A-Z].*")
                || !password.matches(".*[#@$%&*!^].*")) {
            throw new SignUpRestrictedException("SGR-004", "Weak password!");
        } else if (customerDao.getCustomerByContactNumber(contactNumber) != null) {
            throw new SignUpRestrictedException("SGR-001", "This contact number is already registered! Try other contact number.");
        }
        // encrypting the password of the customer
        String[] encryptedText = cryptographyProvider.encrypt(password);
        customerEntity.setSalt(encryptedText[0]);
        customerEntity.setPassword(encryptedText[1]);
        return customerDao.saveCustomer(customerEntity);
    }

    /**
     * method for authenticating the customer using contact number and password
     *
     * @param contactNumber
     * @param password
     * @throws AuthenticationFailedException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity authenticate(String contactNumber, String password)
            throws AuthenticationFailedException {
        CustomerEntity customer = customerDao.getCustomerByContactNumber(contactNumber);
        //checks if the customer has not been registered
        if (customer == null) {
            throw new AuthenticationFailedException("ATH-001", "This contact number has not been registered!");
        }

        final String encryptedPassword = cryptographyProvider.encrypt(password, customer.getSalt());

        String customerPassword = customer.getPassword();
        if (customerPassword != null && customerPassword.equals(encryptedPassword)) {
            CustomerAuthEntity authEntity = new CustomerAuthEntity();
            authEntity.setCustomer(customer);
            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime expiresAt = now.plusHours(8);
            String uuid = customer.getUuid();
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);

            authEntity.setAccessToken(jwtTokenProvider.generateToken(uuid, now, expiresAt));
            authEntity.setLoginAt(now);
            authEntity.setExpiresAt(expiresAt);
            authEntity.setUuid(UUID.randomUUID().toString());
            customerAuthDao.createAuthEntity(authEntity);

            return authEntity;
        } else {
            throw new AuthenticationFailedException("ATH-002", "Invalid Credentials");
        }
    }

    /**
     * method for customer logout using access token
     *
     * @param authorizationToken
     * @throws AuthorizationFailedException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity logout(String authorizationToken) throws AuthorizationFailedException {
        CustomerAuthEntity customerAuthEntity = customerAuthDao.getCustomerAuthToken(authorizationToken);
        //checks if the customer is not logged in
        if (customerAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in.");
        }
        //checks if the customer is not logged out
        if (customerAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "Customer is Logged out.Login in again to access this endpoint.");
        }
        //checks if the session of the logged in customer is expired
        final ZonedDateTime now = ZonedDateTime.now();
        if (customerAuthEntity.getExpiresAt().compareTo(now) <= 0) {
            throw new AuthorizationFailedException("ATHR-003", "Your session is expired.Log in again to access this endpoint.");
        }
        customerAuthEntity.setLogoutAt(now);
        customerAuthEntity = customerAuthDao.customerLogout(customerAuthEntity);
        return customerAuthEntity;
    }

    /**
     * method to getcustomer status
     *
     * @param accessToken
     * @throws AuthorizationFailedException
     */
    public CustomerEntity getCustomer(String accessToken) throws AuthorizationFailedException {
        CustomerAuthEntity customerAuthEntity = customerAuthDao.getCustomerAuthToken(accessToken);
        //checks if the customer is not logged in
        if (customerAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in.");
        }
        //checks if the customer is not logged out
        if (customerAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "Customer is Logged out.Login in again to access this endpoint.");
        }
        //checks if the session of the logged in customer is expired
        final ZonedDateTime now = ZonedDateTime.now();
        if (customerAuthEntity.getExpiresAt().compareTo(now) <= 0) {
            throw new AuthorizationFailedException("ATHR-003", "Your session is expired.Log in again to access this endpoint.");
        }
        return customerAuthEntity.getCustomer();
    }

    /**
     * method to update customer details
     *
     * @param customerEntity
     * @throws UpdateCustomerException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) throws UpdateCustomerException {
        CustomerEntity updatedCustomerEntity = customerDao.getCustomerByUuid(customerEntity.getUuid());
        updatedCustomerEntity.setFirstName(customerEntity.getFirstName());
        updatedCustomerEntity.setLastName(customerEntity.getLastName());
        CustomerEntity newUpdatedCustomer = customerDao.updateCustomer(customerEntity);
        return newUpdatedCustomer;
    }

    /**
     * method to update customer password
     *
     * @param oldPwd
     * @param newPwd
     * @param customerEntity
     * @throws UpdateCustomerException
     * @throws AuthorizationFailedException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity updateCustomerPassword(String oldPwd, String newPwd, CustomerEntity customerEntity)
            throws UpdateCustomerException, AuthorizationFailedException {
        //checks for the validity of password
        if (newPwd.length() < 8
                || !newPwd.matches(".*\\d.*")
                || !newPwd.matches(".*[A-Z].*")
                || !newPwd.matches(".*[#@$%&*!^].*")) {
            throw new UpdateCustomerException("UCR-001", "Weak password!");
        }
        //checks if the old password entered matches or else throws the error
        String encryptedOldPassword = cryptographyProvider.encrypt(oldPwd, customerEntity.getSalt());
        if (encryptedOldPassword.equals(customerEntity.getPassword())) {
            CustomerEntity updatedCustomerEntity = customerDao.getCustomerByUuid(customerEntity.getUuid());

            String[] encryptedPassword = cryptographyProvider.encrypt(newPwd);
            updatedCustomerEntity.setSalt(encryptedPassword[0]);
            updatedCustomerEntity.setPassword(encryptedPassword[1]);

            CustomerEntity newUpdatedCustomerEntity = customerDao.updateCustomer(updatedCustomerEntity);

            return newUpdatedCustomerEntity;

        } else {
            throw new UpdateCustomerException("UCR-004", "Incorrect old password!");
        }
    }
}