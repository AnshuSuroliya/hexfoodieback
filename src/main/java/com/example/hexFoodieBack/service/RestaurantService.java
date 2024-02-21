package com.example.hexFoodieBack.service;


import com.example.hexFoodieBack.entity.Food;
import com.example.hexFoodieBack.request.DeleteFoodRequest;
import com.example.hexFoodieBack.request.MenuRequest;
import com.example.hexFoodieBack.request.RestaurantIdRequest;
import com.example.hexFoodieBack.request.RestaurantNameRequest;
import com.example.hexFoodieBack.response.MenuResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RestaurantService {
    ResponseEntity<MenuResponse> addMenuItems(MenuRequest menuRequest);

    ResponseEntity<MenuResponse> deleteMenuItems(DeleteFoodRequest deleteFoodRequest);

    ResponseEntity<List<Food>> displayMenu(RestaurantIdRequest restaurantIdRequest);

}
