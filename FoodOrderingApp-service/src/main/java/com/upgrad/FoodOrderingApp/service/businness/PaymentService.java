package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.PaymentDAO;
import com.upgrad.FoodOrderingApp.service.entity.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;
    public List<PaymentEntity> getAllPaymentMethods() {
        List<PaymentEntity> paymentEntities = paymentDAO.getAllPaymentMethods();
        return paymentEntities;
    }
}
