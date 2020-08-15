package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.RestaurantDetailsResponseAddress;
import com.upgrad.FoodOrderingApp.api.model.RestaurantDetailsResponseAddressState;
import com.upgrad.FoodOrderingApp.api.model.RestaurantList;
import com.upgrad.FoodOrderingApp.api.model.RestaurantListResponse;
import com.upgrad.FoodOrderingApp.service.businness.CategoryService;
import com.upgrad.FoodOrderingApp.service.businness.RestaurantService;
import com.upgrad.FoodOrderingApp.service.entity.CategoryEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, path = "/restaurant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestaurantListResponse> getAllRestaurants() {
        List<RestaurantEntity> allRestaurants = restaurantService.getAllRestaurantsByRating();
        //declaring a list to store all restaurants
        List<RestaurantList> allRestaurantsListDetails = new LinkedList<>();

        for (RestaurantEntity restaurantEntityDetails : allRestaurants) {
            //Iterate for each restaurant entity

            //Retrieve the categories for each restaurant and should be separated by comma
            List<CategoryEntity> categoryEntities = categoryService.getCategoriesByRestaurant(restaurantEntityDetails.getUuid());
            String categories = new String();
            ListIterator<CategoryEntity> categoryListIterator = categoryEntities.listIterator();
            while (categoryListIterator.hasNext()) {
                categories = categories + categoryListIterator.next().getCategoryname();
                if (categoryListIterator.hasNext()) {
                    categories = categories + ", ";
                }
            }

            //Creating the RestaurantDetailsResponseAddressState for the RestaurantList
            RestaurantDetailsResponseAddressState restaurantDetailsResponseAddressState = new RestaurantDetailsResponseAddressState()
                    .id(UUID.fromString(restaurantEntityDetails.getAddress().getState().getUuid()))
                    .stateName(restaurantEntityDetails.getAddress().getState().getStateName());

            //Creating the RestaurantDetailsResponseAddress for the RestaurantList
            RestaurantDetailsResponseAddress restaurantDetailsResponseAddress = new RestaurantDetailsResponseAddress()
                    .id(UUID.fromString(restaurantEntityDetails.getAddress().getUuid()))
                    .city(restaurantEntityDetails.getAddress().getCity())
                    .flatBuildingName(restaurantEntityDetails.getAddress().getFlatBuildingNumber())
                    .locality(restaurantEntityDetails.getAddress().getLocality())
                    .pincode(restaurantEntityDetails.getAddress().getPincode())
                    .state(restaurantDetailsResponseAddressState);

            //Creating RestaurantList to add to list of RestaurantList
            RestaurantList restaurantList = new RestaurantList()
                    .id(UUID.fromString(restaurantEntityDetails.getUuid()))
                    .restaurantName(restaurantEntityDetails.getRestaurantname())
                    .averagePrice(restaurantEntityDetails.getAveragepricefortwo())
                    .categories(categories)
                    .customerRating(BigDecimal.valueOf(restaurantEntityDetails.getCustomerrating()))
                    .numberCustomersRated(restaurantEntityDetails.getNumberofcustomersrated())
                    .photoURL(restaurantEntityDetails.getPhotourl())
                    .address(restaurantDetailsResponseAddress);

            //Adding it to the list
            allRestaurantsListDetails.add(restaurantList);

        }

        //Creating the RestaurantListResponse by adding the list of RestaurantList
        RestaurantListResponse restaurantListResponse = new RestaurantListResponse().restaurants(allRestaurantsListDetails);
        return new ResponseEntity<RestaurantListResponse>(restaurantListResponse, HttpStatus.OK);
    }
}
