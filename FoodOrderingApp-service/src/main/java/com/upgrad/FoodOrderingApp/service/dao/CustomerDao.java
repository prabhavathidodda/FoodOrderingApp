package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    //To save customer in customer entity
    public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
        entityManager.persist(customerEntity);
        return customerEntity;
    }

    //To retrieve customer details by contact number
    public CustomerEntity getCustomerByContactNumber(String customerContactNumber) {
        try {
            return entityManager.createNamedQuery("customerByContactNumber", CustomerEntity.class).setParameter("contactNumber", customerContactNumber)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    //To retrieve customer details by customer uuid
    public CustomerEntity getCustomerByUuid(final String uuid) {
        try {
            CustomerEntity customer = entityManager.createNamedQuery("customerByUuid", CustomerEntity.class).setParameter("uuid", uuid).getSingleResult();
            return customer;
        } catch (NoResultException nre) {
            return null;
        }
    }

    //To update customer details in customer entity
    public CustomerEntity updateCustomer(CustomerEntity updatedCustomer) {
        entityManager.merge(updatedCustomer);
        return updatedCustomer;
    }

}


