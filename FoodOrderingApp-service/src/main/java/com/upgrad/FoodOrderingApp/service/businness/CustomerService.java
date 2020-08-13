package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.CustomerAddressDAO;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
<<<<<<< HEAD
=======
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import com.upgrad.FoodOrderingApp.service.exception.UpdateCustomerException;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 425183ac5d39b6060363697abb1e731965acdb08
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerAddressDAO customerAddressDAO;

<<<<<<< HEAD
    CustomerAddressEntity customerAddressEntity;
=======
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAuthEntity logout(String authorizationToken) throws AuthorizationFailedException {
        CustomerAuthEntity customerAuthEntity = customerAuthDao.getCustomerAuthToken(authorizationToken);
        if (customerAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in.");
        }
        if (customerAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "Customer is Logged out.Login in again to access this endpoint");
        }
        final ZonedDateTime now = ZonedDateTime.now();
        if (customerAuthEntity.getExpiresAt().compareTo(now) <= 0) {
            throw new AuthorizationFailedException("ATHR-003", "Your session is expired.Log in again to access this endpoint");
        }
        customerAuthEntity.setLogoutAt(now);
        customerAuthEntity = customerAuthDao.customerLogout(customerAuthEntity);
        return customerAuthEntity;
>>>>>>> 425183ac5d39b6060363697abb1e731965acdb08

    CustomerEntity customerEntity;

    CustomerAuthEntity customerAuthEntity;

<<<<<<< HEAD


=======
    public CustomerEntity getCustomer(String accessToken) throws AuthorizationFailedException {
        CustomerAuthEntity customerAuthEntity = customerAuthDao.getCustomerAuthToken(accessToken);
        if (customerAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "Customer is not Logged in.");
        }
        if (customerAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "Customer is Logged out.Login in again to access this endpoint");
        }
        final ZonedDateTime now = ZonedDateTime.now();
        if (customerAuthEntity.getExpiresAt().compareTo(now) <= 0) {
            throw new AuthorizationFailedException("ATHR-003", "Your session is expired.Log in again to access this endpoint");
        }
        return customerAuthEntity.getCustomer();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity updateCustomer(CustomerEntity customerEntity) throws UpdateCustomerException {
        CustomerEntity updatedCustomerEntity = customerDao.getCustomerByUuid(customerEntity.getUuid());
        updatedCustomerEntity.setFirstName(customerEntity.getFirstName());
        updatedCustomerEntity.setLastName(customerEntity.getLastName());
        CustomerEntity newUpdatedCustomer = customerDao.updateCustomer(customerEntity);
        return newUpdatedCustomer;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerEntity updateCustomerPassword(String oldPwd, String newPwd, CustomerEntity customerEntity) throws UpdateCustomerException, AuthorizationFailedException {
        if (oldPwd == null || newPwd == null) {
            throw new UpdateCustomerException("UCR-003", "No field should be empty");
        }
        if (newPwd.length() < 8
                || !newPwd.matches(".*\\d.*")
                || !newPwd.matches(".*[A-Z].*")
                || !newPwd.matches(".*[#@$%&*!^].*")) {
            throw new UpdateCustomerException("UCR-001", "Weak password!");
        }
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
>>>>>>> 425183ac5d39b6060363697abb1e731965acdb08
}
