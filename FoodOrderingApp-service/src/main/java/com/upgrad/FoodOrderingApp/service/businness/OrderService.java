package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.CouponDAO;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.dao.OrderDAO;
import com.upgrad.FoodOrderingApp.service.entity.CouponEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrdersEntity;
import com.upgrad.FoodOrderingApp.service.exception.CouponNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    CouponDAO couponDAO;

    @Autowired
    CustomerDao customerDao;

    /**
     * Fetch coupon details by coupon name
     *
     * @param couponName Coupon name
     * @return CouponEntity object
     * @throws CouponNotFoundException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CouponEntity getCouponByCouponName(String couponName) throws CouponNotFoundException {

        if (couponName == null || couponName.isEmpty()) {
            throw new CouponNotFoundException("CPF-002", "Coupon name field should not be empty");
        }
        CouponEntity couponEntity = couponDAO.getCouponByCouponName(couponName);
        if (couponEntity == null) {
            throw new CouponNotFoundException("CPF-001", "No coupon by this name");
        }
        return couponEntity;
    }


    /**
     * Fetch orders by customer
     *
     * @param customerUuid
     * @return
     */
    public List<OrdersEntity> getOrdersByCustomers(String customerUuid) {

        CustomerEntity customerEntity = customerDao.getCustomerByUuid(customerUuid);
        List<OrdersEntity> ordersEntityList = orderDAO.getOrdersByCustomers(customerEntity);
        return ordersEntityList;
    }

}
