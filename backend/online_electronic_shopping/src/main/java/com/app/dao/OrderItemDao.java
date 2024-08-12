package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.Seller;

public interface OrderItemDao extends JpaRepository<OrderItem, Long>{
	List<OrderItem> findBySeller(Seller seller);
	@Query("SELECT o FROM OrderItem o WHERE o.seller.id = :sellerId")
	   List<OrderItem> findOrdersBySellerId(@Param("sellerId") Long sellerId);
}
