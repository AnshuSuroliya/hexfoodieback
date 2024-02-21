package com.example.hexFoodieBack.controller;

import com.example.hexFoodieBack.entity.Restaurant;
import com.example.hexFoodieBack.repository.RestaurantRepository;
import com.example.hexFoodieBack.request.NearByRequest;
import com.example.hexFoodieBack.request.RestaurantNameRequest;
import com.example.hexFoodieBack.request.LocationRequest;
import com.example.hexFoodieBack.request.RestaurantRequest;
import com.example.hexFoodieBack.response.RestaurantResponse;
import com.example.hexFoodieBack.service.SuperAdminServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/superAdmin")
public class SuperAdminController {

    @Autowired
    SuperAdminServiceImple superAdminServiceImple;

    @Autowired
    RestaurantRepository restaurantRepository;

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantResponse> addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return superAdminServiceImple.addRestaurant(restaurantRequest);
    }

    @PostMapping("/deleteRestaurant")
    public ResponseEntity<RestaurantResponse> deleteRestaurant(@RequestBody RestaurantNameRequest restaurantNameRequest){
        return superAdminServiceImple.deleteRestaurant(restaurantNameRequest);
    }

    @PostMapping("/updateRestaurant")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return superAdminServiceImple.updateRestaurant(restaurantRequest);
    }
    @PostMapping("/displayRestaurant")
    public ResponseEntity<List<Restaurant>> display(@RequestBody LocationRequest locationRequest){
        return superAdminServiceImple.displayRestaurant(locationRequest);
    }

    @GetMapping("/display")
    public ResponseEntity<List<Restaurant>> show(){
        return new ResponseEntity<>(restaurantRepository.findAll(), HttpStatus.OK);
    }

//    @PostMapping("/nearby")
//    public ResponseEntity<List<Restaurant>> nearBy(@RequestBody NearByRequest nearByRequest){
//        return superAdminServiceImple.findNearByRestaurant(nearByRequest);
//    }
}
