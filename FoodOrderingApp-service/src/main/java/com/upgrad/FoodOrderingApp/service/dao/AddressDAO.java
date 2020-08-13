package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressDAO {

  @PersistenceContext private EntityManager entityManager;

  /**
   *Method to save address in Address entity
   *
   * @param addressEntity
   * @return AddressEntity object
   */
  @Transactional
  public AddressEntity saveAddress(AddressEntity addressEntity) {
    entityManager.persist(addressEntity);
    return addressEntity;
  }

//  /**
//   *
//   * @return
//   */
//  @Transactional
//  public List<AddressEntity> getAllSavedAddresses() {
//    try {
//      Query query = entityManager.createQuery("from address", AddressEntity.class);
//      return query.getResultList();
//    } catch (NoResultException nre) {
//      return null;
//    }
//  }
}
