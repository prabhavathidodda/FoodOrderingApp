//package com.upgrad.FoodOrderingApp.api.controller;
//
//import com.upgrad.FoodOrderingApp.api.model.CouponDetailsResponse;
//import com.upgrad.FoodOrderingApp.api.model.SaveOrderResponse;
//import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
//import com.upgrad.FoodOrderingApp.service.entity.CouponEntity;
//import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
//import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
//import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
////import java.util.UUID;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/")
//public class OrderController {
//
//    @Autowired
//    CustomerService customerService;
//
//    /**
//     *
//     * @param authorization
//     * @param couponName
//     * @return
//     * @throws AuthorizationFailedException
//     * @throws AddressNotFoundException
//     */
//    @CrossOrigin
//    @RequestMapping(
//            method = RequestMethod.GET,
//            path = "/order/coupon/{coupon_name}",
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<SaveOrderResponse> getCouponByCouponName(
//            @RequestHeader("authorization") final String authorization,
//            @PathVariable(value = "coupon_name") final String couponName)
//            throws AuthorizationFailedException, AddressNotFoundException {
//
//        String bearerToken = authorization.split("Bearer ")[1];
//        CustomerEntity customerEntity = customerService.getCustomer(bearerToken);
//        CouponEntity couponEntity = orderService.getCouponByCouponName(couponName);
//        CouponDetailsResponse couponDetailsResponse = new CouponDetailsResponse().couponName(couponEntity.getCouponName()).id(UUID.fromString(couponEntity.getUuid())).percent(couponEntity.getPercent());
//
//    }
//}