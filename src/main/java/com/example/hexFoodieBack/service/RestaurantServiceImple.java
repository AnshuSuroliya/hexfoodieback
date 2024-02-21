package com.example.hexFoodieBack.service;


import com.example.hexFoodieBack.entity.Food;
import com.example.hexFoodieBack.entity.Restaurant;
import com.example.hexFoodieBack.repository.FoodRepository;
import com.example.hexFoodieBack.repository.RestaurantRepository;
import com.example.hexFoodieBack.request.*;
import com.example.hexFoodieBack.response.MenuResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class RestaurantServiceImple implements RestaurantService{

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

//    private MenuResponse menuResponse;

    @Override
    public ResponseEntity<MenuResponse> addMenuItems(MenuRequest menuRequest) {
        Food food=foodRepository.findFoodByName(menuRequest.getName());
        Restaurant restaurant=restaurantRepository.findByName(menuRequest.getRestaurantName());
        if(food==null){
            Food food1=new Food();
            food1.setName(menuRequest.getName());
            food1.setCategory(menuRequest.getCategory());
            food1.setImageUrl(menuRequest.getImageUrl());
            food1.setPrice(menuRequest.getPrice());
            food1.setRestaurantId(restaurant);
            foodRepository.save(food1);
            MenuResponse menuResponse=new MenuResponse();
            menuResponse.setMessage("Food Item added successfully");
            menuResponse.setSuccess(true);
            return new ResponseEntity<>(menuResponse, HttpStatus.OK);
        }
        else {
            MenuResponse menuResponse=new MenuResponse();
            menuResponse.setMessage("Food Item already present");
            menuResponse.setSuccess(false);
            return new ResponseEntity<>(menuResponse,HttpStatus.OK);
        }
    }
    public ResponseEntity<MenuResponse> deleteMenuItems(DeleteFoodRequest deleteFoodRequest){
        Food food=foodRepository.findFoodByName(deleteFoodRequest.getName());
        if(food==null){
            MenuResponse menuResponse=new MenuResponse();
            menuResponse.setMessage("No food item found");
            menuResponse.setSuccess(false);
            return new ResponseEntity<>(menuResponse,HttpStatus.OK);
        }
        foodRepository.delete(food);
        MenuResponse menuResponse=new MenuResponse();
        menuResponse.setSuccess(true);
        menuResponse.setMessage("Food Item deleted Successfully");
        return new ResponseEntity<>(menuResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Food>> displayMenu(RestaurantIdRequest restaurantIdRequest) {
        List<Food> foods=foodRepository.findByRestaurant(restaurantIdRequest.getRestaurantId());
        log.info(foods.toString());
        if(foods==null){
            return null;
        }
        return new ResponseEntity<>(foods,HttpStatus.OK);
    }

}
