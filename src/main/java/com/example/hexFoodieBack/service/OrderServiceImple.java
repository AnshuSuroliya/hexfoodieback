package com.example.hexFoodieBack.service;

import com.example.hexFoodieBack.entity.*;
import com.example.hexFoodieBack.repository.AddressRepository;
import com.example.hexFoodieBack.repository.OrderItemRepository;
import com.example.hexFoodieBack.repository.OrderRepository;
import com.example.hexFoodieBack.repository.UserRepository;
import com.example.hexFoodieBack.request.OrderRequest;
import com.example.hexFoodieBack.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImple implements OrderService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartService cartService;

    @Override
    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) {
        User user=userRepository.findByEmail(orderRequest.getEmail());
        Address address1=new Address();
        address1.setState(orderRequest.getState());
        address1.setCity(orderRequest.getCity());
        address1.setZipcode(orderRequest.getZipcode());
        address1.setAddressLine1(orderRequest.getAddressLine1());
        address1.setAddressLine2(orderRequest.getAddressLine2());
        address1.setAddress_user(user);
        Address address=addressRepository.save(address1);
//        List<Address> addresses  = user.getAddress();
//        if(addresses==null){
//            addresses =new ArrayList<Address>();
//        }
//        addresses.add(address1);
        user.getAddress().add(address1);
        userRepository.save(user);
        Cart cart=cartService.findUserCart(user.getEmail());
        List<OrderItems> orderItems=new ArrayList<>();
        for(CartItems items:cart.getCartItems()){
            OrderItems orderItem=new OrderItems();
            orderItem.setPrice(items.getPrice());
            orderItem.setFood(items.getFood());
            orderItem.setQuantity(items.getQuantity());
            OrderItems createdItem=orderItemRepository.save(orderItem);
            orderItems.add(createdItem);
        }
        Order createOrder=new Order();
        createOrder.setUser(user);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        //createOrder.getPaymentDetails().setRazorpayPaymentStatus("Pending");
        createOrder.setAddress(address);
        createOrder.setTotalItems(cart.getTotalItems());
        Order saveOrder=orderRepository.save(createOrder);
        for(OrderItems item:orderItems){
            item.setOrder(saveOrder);
            orderItemRepository.save(item);
        }
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setMessage("Order Placed Successfully");
        orderResponse.setSuccess(true);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
