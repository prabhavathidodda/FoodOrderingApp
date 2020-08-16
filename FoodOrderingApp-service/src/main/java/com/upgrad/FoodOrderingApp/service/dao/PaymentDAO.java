package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PaymentDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public List<PaymentEntity> getAllPaymentMethods() {
        try {
            List<PaymentEntity> paymentEntities =entityManager.createNamedQuery("getAllPaymentMethods", PaymentEntity.class).getResultList();
            return paymentEntities;
        }catch (NoResultException nre){
            return null;
        }
    }
}