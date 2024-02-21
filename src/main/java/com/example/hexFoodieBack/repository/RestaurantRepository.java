package com.example.hexFoodieBack.repository;

import com.example.hexFoodieBack.entity.Restaurant;
import com.example.hexFoodieBack.request.NearByRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    @Query("Select r from Restaurant r Where r.name=:name")
    Restaurant findByName(@Param("name") String name);
    @Query("Select r from Restaurant r Where r.area=:area")
    List<Restaurant> findByLocation(@Param("area") String area);

//    List<Restaurant> findByDistance(NearByRequest nearByRequest);
}
