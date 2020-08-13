package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class StateDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Method to find UUID in state table
     *
     * @param uuid Randomly generated UUID
     * @return State entity object
     */
    public StateEntity findStateByUuid(String uuid) {
        StateEntity stateEntity;
        try {
            stateEntity =
                    entityManager
                            .createNamedQuery("findStateByUuid", StateEntity.class)
                            .setParameter("uuid", uuid)
                            .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
        return stateEntity;
    }
}
