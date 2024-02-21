package com.example.hexFoodieBack.service;

import com.example.hexFoodieBack.entity.Cart;
import com.example.hexFoodieBack.entity.User;
import com.example.hexFoodieBack.request.CartItemRequest;
import com.example.hexFoodieBack.request.EmailRequest;
import com.example.hexFoodieBack.response.CartResponse;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<CartResponse> createCart(String email);
    ResponseEntity<CartResponse> addCartItem(CartItemRequest cartItemRequest);

    Cart findUserCart(String email);

    ResponseEntity<CartResponse> removeCartItem(CartItemRequest cartItemRequest);

    ResponseEntity<Cart> displayCart(EmailRequest emailRequest);
}
