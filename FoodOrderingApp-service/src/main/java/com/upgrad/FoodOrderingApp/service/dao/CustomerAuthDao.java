package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerAuthDao {

    @PersistenceContext
    private EntityManager entityManager;

    //To create customer auth entity
    public CustomerAuthEntity createAuthEntity(CustomerAuthEntity authEntity) {
        entityManager.persist(authEntity);
        return authEntity;
    }

    //To update customer logout
    public CustomerAuthEntity customerLogout(final CustomerAuthEntity customerAuthEntity) {
        entityManager.merge(customerAuthEntity);
        return customerAuthEntity;
    }

    //To retrieve details of customer by access token
    public CustomerAuthEntity getCustomerAuthToken(String authorization) {
        try {
            CustomerAuthEntity customerAuthEntity = entityManager.createNamedQuery("getCustomerAuthToken", CustomerAuthEntity.class).setParameter("accessToken", authorization).getSingleResult();
            return customerAuthEntity;
        } catch (NoResultException nre) {
            return null;
        }

    }

}
