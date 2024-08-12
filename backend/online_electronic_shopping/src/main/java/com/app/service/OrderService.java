package com.app.service;

import java.util.List;

import com.app.dto.OrderItemResponseDTO;
import com.app.dto.OrderRequestDTO;
import com.app.entities.Customer;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.Payment;
import com.app.entities.Seller;

public interface OrderService {
	public String createOrder(OrderRequestDTO orderRequestDTO,Long customerId);
    public List<OrderItemResponseDTO> getOrdersByCustomer(Long customerId);
    public List<OrderItemResponseDTO> getOrderItemsBySeller(Long sellerId);
	public Payment addPayment(Payment payment);
}
