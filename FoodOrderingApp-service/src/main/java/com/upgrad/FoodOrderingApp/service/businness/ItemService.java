package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.ItemDAO;
import com.upgrad.FoodOrderingApp.service.entity.ItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {

    @Autowired
    ItemDAO itemDao;


    public List<ItemEntity> getItemsByPopularity(RestaurantEntity restaurantEntity) {

        List<ItemEntity> itemEntities = itemDao.getTopItems(restaurantEntity, 5);

        return itemEntities;
    }
}