package com.upgrad.FoodOrderingApp.service.businness;


import com.upgrad.FoodOrderingApp.service.dao.CustomerAddressDAO;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    CustomerAddressDAO customerAddressDAO;

    CustomerAddressEntity customerAddressEntity;

    CustomerEntity customerEntity;

    CustomerAuthEntity customerAuthEntity;



}
