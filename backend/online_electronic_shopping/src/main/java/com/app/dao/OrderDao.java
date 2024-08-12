package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Customer;
import com.app.entities.Order;

public interface OrderDao extends JpaRepository<Order, Long>{
	 List<Order> findByCustomer(Customer customer);
	 @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
	   List<Order> findOrdersByCustomerId(@Param("customerId") Long customerId);
	 
	 
}
