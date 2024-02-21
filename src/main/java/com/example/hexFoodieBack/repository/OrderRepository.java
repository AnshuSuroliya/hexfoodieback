package com.example.hexFoodieBack.repository;

import com.example.hexFoodieBack.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
