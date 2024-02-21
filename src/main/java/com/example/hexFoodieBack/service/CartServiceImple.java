package com.example.hexFoodieBack.service;

import com.example.hexFoodieBack.entity.Cart;
import com.example.hexFoodieBack.entity.CartItems;
import com.example.hexFoodieBack.entity.Food;
import com.example.hexFoodieBack.entity.User;
import com.example.hexFoodieBack.repository.CartItemRepository;
import com.example.hexFoodieBack.repository.CartRepository;
import com.example.hexFoodieBack.repository.FoodRepository;
import com.example.hexFoodieBack.repository.UserRepository;
import com.example.hexFoodieBack.request.CartItemRequest;
import com.example.hexFoodieBack.request.EmailRequest;
import com.example.hexFoodieBack.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImple implements CartService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    CartItemRepository cartItemRepository;

//    private CartResponse cartResponse;

    @Override
    public ResponseEntity<CartResponse> createCart(String email) {
        User user = userRepository.findByEmail(email);
        if(user==null){
            CartResponse cartResponse=new CartResponse();
            cartResponse.setMessage("User not found");
            cartResponse.setSuccess(false);
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        System.out.println(user);
        Cart cart=new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        CartResponse cartResponse=new CartResponse();
        cartResponse.setMessage("Cart Created Successfully");
        cartResponse.setSuccess(true);
        return new ResponseEntity<>(cartResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CartResponse> addCartItem(CartItemRequest cartItemRequest) {
        Cart cart=cartRepository.findUserByEmail(cartItemRequest.getEmail());
        System.out.println(cart);
        System.out.println(cartItemRequest.getId());
        Food food=foodRepository.findFoodById(cartItemRequest.getId());
//        CartItems exist=cartItemRepository.cartItemExist(cartItemRequest.getEmail(),food,cart);
//        if(exist==null) {
            // System.out.println(addItemRequest.getQuantity() +"   "+product.getPrice());
            CartItems cartItems = new CartItems();
            cartItems.setFood(food);
            cartItems.setCart(cart);

            cartItems.setQuantity(1);
            cartItems.setPrice(food.getPrice()*cartItems.getQuantity());
            cartItemRepository.save(cartItems);
            cart.getCartItems().add(cartItems);
//        }
//        else {
//            cartResponse.setMessage("Food Items already present in cart");
//            cartResponse.setSuccess(false);
//            return new ResponseEntity<>(cartResponse,HttpStatus.OK);
//        }
        Cart cart1=findUserCart(cartItemRequest.getEmail());
        cartRepository.save(cart1);
        CartResponse cartResponse=new CartResponse();
        cartResponse.setMessage("Food Items added to cart successfully");
        cartResponse.setSuccess(true);
        return new ResponseEntity<>(cartResponse,HttpStatus.OK);
    }

    @Override
    public Cart findUserCart(String email){
            Cart cart=cartRepository.findUserByEmail(email);
            int totalPrice=0;
            int totalItem=0;
            for(CartItems cartItems:cart.getCartItems()){
                totalPrice+=cartItems.getPrice();
                totalItem+=cartItems.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            cart.setTotalItems(totalItem);
            return cartRepository.save(cart);
    }

    @Override
    public ResponseEntity<CartResponse> removeCartItem(CartItemRequest cartItemRequest) {
        System.out.println(cartItemRequest.getId());
        Optional<CartItems> cartItems=cartItemRepository.findById(cartItemRequest.getId());
        User user1=userRepository.findByEmail(cartItems.get().getEmail());
        User user=userRepository.findByEmail(cartItemRequest.getEmail());
        if(user==user1) {
            cartItemRepository.deleteById(cartItemRequest.getId());
            Cart cart = findUserCart(cartItemRequest.getEmail());
            cartRepository.save(cart);
        }
        CartResponse cartResponse=new CartResponse();
        cartResponse.setMessage("Item removed");
        cartResponse.setSuccess(true);
        return new ResponseEntity<>(cartResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> displayCart(EmailRequest emailRequest) {
        Cart cart=cartRepository.findUserByEmail(emailRequest.getEmail());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }
}
