package com.example.hexFoodieBack.repository;

import com.example.hexFoodieBack.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("Select o from Order o Where o.user.email=:email")
    List<Order> findByUserEmail(@Param("email") String email);
}
