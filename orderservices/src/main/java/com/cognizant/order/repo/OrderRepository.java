package com.cognizant.order.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	
}