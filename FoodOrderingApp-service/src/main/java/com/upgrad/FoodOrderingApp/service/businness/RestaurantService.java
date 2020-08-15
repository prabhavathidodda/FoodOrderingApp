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
    //Method to get restaurants list by rating
    public List<RestaurantEntity> getAllRestaurantsByRating(){
        List<RestaurantEntity> restaurantListByRating = restaurantDAO.getAllRestaurantsByRating();
        return restaurantListByRating;
    }

    //Method to get restaurants list by Name entered by user. If restaurant name field entered by the customer is empty, throw RestaurantNotFoundException
    public List<RestaurantEntity> getRestaurantsByName(String restaurantName)throws RestaurantNotFoundException{
        if(restaurantName == null || restaurantName ==""){
            throw new RestaurantNotFoundException("RNF-003","Restaurant name field should not be empty");
        }
        List<RestaurantEntity> restaurantNamesList = restaurantDAO.getRestaurantsByName(restaurantName);
        return restaurantNamesList;
    }

}
