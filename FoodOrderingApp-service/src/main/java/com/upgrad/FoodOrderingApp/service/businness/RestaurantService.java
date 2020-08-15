package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.RestaurantDAO;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantDAO restaurantDAO;
    @Transactional(propagation = Propagation.REQUIRED)
    //Method to get restaurants list
    public List<RestaurantEntity> getAllRestaurantsByRating(){
        List<RestaurantEntity> restaurantListByRating = restaurantDAO.getAllRestaurantsByRating();
        return restaurantListByRating;
    }
}
