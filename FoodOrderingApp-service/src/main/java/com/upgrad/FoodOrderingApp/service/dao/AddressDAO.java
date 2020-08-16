package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;
=======
>>>>>>> master

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class AddressDAO {

    @PersistenceContext
    private EntityManager entityManager;

<<<<<<< HEAD
    /**
     * Method to save address in Address entity
     *
     * @param addressEntity
     * @return AddressEntity object
     */
    @Transactional
    public AddressEntity saveAddress(AddressEntity addressEntity) {
        entityManager.persist(addressEntity);
        return addressEntity;
    }

    /**
     * @param addressEntity
     * @return AddressEntity object
     */
    @Transactional
    public AddressEntity deleteAddress(AddressEntity addressEntity) {
        entityManager.remove(addressEntity);
        return addressEntity;
    }

    @Transactional
    public AddressEntity getAddressByUUID(String uuid) {
      AddressEntity addressEntity;
        try {
          addressEntity = entityManager.createNamedQuery("getAddressByUuid", AddressEntity.class).setParameter("uuid", uuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
      return addressEntity;
    }

    public AddressEntity updateAddressActiveStatus(AddressEntity addressEntity) {
        entityManager.merge(addressEntity);
        return addressEntity;
    }

}
=======
    public AddressEntity getAddressById(final Integer addressId) {
        try {
            return entityManager.createNamedQuery("getAddressById", AddressEntity.class).setParameter("id", addressId)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }

    }

}
>>>>>>> master
