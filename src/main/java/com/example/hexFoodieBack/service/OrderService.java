package com.example.hexFoodieBack.service;

import com.example.hexFoodieBack.request.OrderRequest;
import com.example.hexFoodieBack.response.OrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest);
}
