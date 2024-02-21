package com.example.hexFoodieBack.service;

import com.example.hexFoodieBack.entity.Restaurant;
import com.example.hexFoodieBack.request.RestaurantNameRequest;
import com.example.hexFoodieBack.request.LocationRequest;
import com.example.hexFoodieBack.request.RestaurantRequest;
import com.example.hexFoodieBack.response.RestaurantResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SuperAdminService {

    ResponseEntity<RestaurantResponse> addRestaurant(RestaurantRequest restaurantRequest);
    ResponseEntity<RestaurantResponse> deleteRestaurant(RestaurantNameRequest restaurantNameRequest);

    ResponseEntity<RestaurantResponse> updateRestaurant(RestaurantRequest restaurantRequest);

    ResponseEntity<List<Restaurant>> displayRestaurant(LocationRequest locationRequest);
}
