package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerAddressDAO {

  @PersistenceContext private EntityManager entityManager;

  /**
   * Method to fetch customer address by customer from CustomerAddress table
   *
   * @param customerEntity
   * @return On success return CustomerAddress entity object. Null on exception
   */
  public List<CustomerAddressEntity> getAllCustomerAddressByCustomer(
      CustomerEntity customerEntity) {
    try {
      Integer active = 1;
      List<CustomerAddressEntity> customerAddressEntities =
          entityManager
              .createNamedQuery("getAllCustomerAddressByCustomer", CustomerAddressEntity.class)
              .setParameter("customer_entity", customerEntity)
              .setParameter("active", active)
              .getResultList();
      return customerAddressEntities;
    } catch (NoResultException nre) {
      return null;
    }
  }

  /**
   * Method to fetch customer address by address from CustomerAddress table
   *
   * @param addressEntity
   * @return On success return CustomerAddress entity object, On failure or exception, return NULL
   */
  public CustomerAddressEntity getCustomerAddressByAddress(AddressEntity addressEntity) {
    try {
      CustomerAddressEntity customerAddressEntity =
          entityManager
              .createNamedQuery("getCustomerAddressByAddress", CustomerAddressEntity.class)
              .setParameter("address_entity", addressEntity)
              .getSingleResult();
      return customerAddressEntity;
    } catch (NoResultException nre) {
      return null;
    }
  }

  /**
   * Method to save customer address in CustomerAddress entity
   *
   * @param customerAddressEntity CustomerAddress entity argument
   * @return CustomerAddress object
   */
  public CustomerAddressEntity saveCustomerAddress(CustomerAddressEntity customerAddressEntity) {
    entityManager.persist(customerAddressEntity);
    return customerAddressEntity;
  }
}
