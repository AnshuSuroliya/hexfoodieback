package com.example.hexFoodieBack.repository;

import com.example.hexFoodieBack.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItems,Long> {
    Optional<CartItems> findById(Long id);
//    @Query("SELECT i from CartItems i Where i.cart=:cart And i.food=:food And i.=:userId")
//    CartItems cartItemExist(@Param("userId") String email, @Param("food")Food food, @Param("cart") Cart cart);
}
