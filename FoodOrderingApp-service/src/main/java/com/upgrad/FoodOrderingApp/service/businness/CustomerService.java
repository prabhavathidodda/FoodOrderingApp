package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

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

    public CustomerAuthEntity authenticate(String password, String correctPassword) {
        return null;
    }

    public CustomerAuthEntity logout(String accessToken) {
        return null;
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
