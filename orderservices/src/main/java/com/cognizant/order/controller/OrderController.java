package com.cognizant.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cognizant.order.exception.AuthorizationException;
import com.cognizant.order.model.Order;
import com.cognizant.order.repo.OrderRepository;


import java.util.Optional;

@RestController
public class OrderController {

    private OrderRepository repo;

    @Autowired
    public OrderController(OrderRepository repo){
        this.repo = repo;
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order,
    		@RequestHeader(value = "Authorization", required = true)String requestTokenHeader) throws AuthorizationException{
        return repo.save(order);
    }

    @GetMapping("/orders/{id}")
    public Optional<Order> findOrderById(@PathVariable Long id,
    		@RequestHeader(value = "Authorization", required = true)String requestTokenHeader) throws AuthorizationException{
    	return repo.findById(id);
    	
    }

}