package com.example.hexFoodieBack.controller;

import com.example.hexFoodieBack.entity.Food;
import com.example.hexFoodieBack.entity.Restaurant;
import com.example.hexFoodieBack.request.*;
import com.example.hexFoodieBack.response.MenuResponse;
import com.example.hexFoodieBack.service.RestaurantServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/admin")
public class RestaurantAdminController {
    @Autowired
    RestaurantServiceImple restaurantServiceImple;

    @PostMapping("/addfooditem")
    public ResponseEntity<MenuResponse> addFoodItem(@RequestBody MenuRequest menuRequest){
        return restaurantServiceImple.addMenuItems(menuRequest);
    }

    @PostMapping("/deleteFoodItem")
    public ResponseEntity<MenuResponse> deleteFooditem(@RequestBody DeleteFoodRequest deleteFoodRequest){
        return restaurantServiceImple.deleteMenuItems(deleteFoodRequest);
    }

    @PostMapping("/displaymenu")
    public ResponseEntity<List<Food>> displayFood(@RequestBody RestaurantIdRequest restaurantIdRequest){
        return restaurantServiceImple.displayMenu(restaurantIdRequest);
    }


}
