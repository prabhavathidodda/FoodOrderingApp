package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CustomerAuthDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
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

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) throws SignUpRestrictedException {

        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";

        String contactNumber = customerEntity.getContactNumber();
        String email = customerEntity.getEmail();
        String password = customerEntity.getPassword();
        if (customerEntity.getFirstName() == null
                || email == null
                || contactNumber == null
                || password == null) {
            throw new SignUpRestrictedException("SGR-005", "Except last name all fields should be filled");
        } else if (!email.matches(emailRegex)) {
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

        // Encryption of password
        String[] encryptedText = cryptographyProvider.encrypt(password);
        customerEntity.setSalt(encryptedText[0]);
        customerEntity.setPassword(encryptedText[1]);

        // Called customerDao to insert new customer record in the database
        return customerDao.saveCustomer(customerEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity authenticate(String contactNumber, String password) throws AuthenticationFailedException {
        CustomerEntity customer = customerDao.getCustomerByContactNumber(contactNumber);


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

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity logout(String authorizationToken) throws AuthorizationFailedException{
        CustomerAuthEntity customerAuthEntity = customerAuthDao.getCustomerAuthToken(authorizationToken);
        if (customerAuthEntity == null) {
            throw new AuthorizationFailedException("ATH-001", "Customer is not Logged in.");
        }
        if ( customerAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATH-002", "Customer is Logged out.Login in again to access this endpoint");
        }
        final ZonedDateTime now = ZonedDateTime.now();
        if(customerAuthEntity.getExpiresAt().compareTo(now) < 0){
           throw new AuthorizationFailedException("ATH-003", "Your session is expired.Log in again to access this endpoint");
       }
        customerAuthEntity.setLogoutAt(now);
        customerAuthEntity = customerAuthDao.customerLogout(customerAuthEntity);
        return customerAuthEntity;


    }

    public CustomerEntity getCustomer(String accessToken) {
        return null;
    }

    public CustomerEntity updateCustomer(CustomerEntity customerEntity) {
        return customerEntity;
    }

    public CustomerEntity updateCustomerPassword(String oldPwd, String newPwd, CustomerEntity customerEntity) {
        return customerEntity;
    }
}
