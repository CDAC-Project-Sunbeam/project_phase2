package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AddressDao;
import com.app.dao.CustomerDao;
import com.app.dao.OrderDao;
import com.app.dao.OrderItemDao;
import com.app.dao.PaymentDao;
import com.app.dao.ProductDao;
import com.app.dto.AddressDTO;
import com.app.dto.OrderItemRequestDTO;
import com.app.dto.OrderItemResponseDTO;
import com.app.dto.OrderRequestDTO;
import com.app.entities.Address;
import com.app.entities.Customer;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.OrderStatus;
import com.app.entities.Payment;
import com.app.entities.Product;
import com.app.entities.Seller;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	 	@Autowired
	    private OrderDao orderDao;

	    @Autowired
	    private OrderItemDao orderItemDao;

	    @Autowired
	    private PaymentDao paymentDao;
	    
	    @Autowired
	    private CustomerDao customerDao;

	    @Autowired
		private ModelMapper modelMapper;
	    
	    @Autowired
	    private ProductDao productDao;
	    
	    @Autowired
	    private AddressDao addressDao;
	    public String createOrder(OrderRequestDTO orderRequestDTO,Long customerId) {
	    	Order order=new Order();
	    	Customer customer=customerDao.findById(customerId).orElseThrow();
	    	order.setCustomer(customer);
	    	order.setStatus(OrderStatus.valueOf("ORDER_PLACED"));
	    	Address shippingAddress = new Address();
	    	shippingAddress.setAdrLine1(orderRequestDTO.getShippingAddress().getAdrLine1());
	    	shippingAddress.setAdrLine2(orderRequestDTO.getShippingAddress().getAdrLine2());
	    	shippingAddress.setCity(orderRequestDTO.getShippingAddress().getCity());
	    	shippingAddress.setState(orderRequestDTO.getShippingAddress().getState());
	    	shippingAddress.setCountry(orderRequestDTO.getShippingAddress().getCountry());
	    	shippingAddress.setZipCode(orderRequestDTO.getShippingAddress().getZipCode());
	        // Persist the new address to the database and set it to the order
	        addressDao.save(shippingAddress);
	        order.setShippingAddress(shippingAddress);
	    	List<OrderItem> orderItems=new ArrayList<OrderItem>();
	    	for (OrderItemRequestDTO orderDtoItem : orderRequestDTO.getOrderItemRequestDTO()) {
				OrderItem orderItem= modelMapper.map(orderDtoItem, OrderItem.class);
				Product product=productDao.findById(orderDtoItem.getProductId()).orElseThrow();
				double p=(orderItem.getPrice()*product.getDiscount())/100;
				orderItem.setPrice(orderItem.getPrice()-p);
				
				orderItem.setProduct(product);
				orderItem.setOrder(order);
				orderItem.setSeller(product.getSeller());
				orderItems.add(orderItem);
			}
	    	order.setOrderItems(orderItems);    	
	         orderDao.save(order);
	         return "order placed";
	    }

	    public List<OrderItemResponseDTO> getOrdersByCustomer(Long customerId) {
	    //	Customer customer=customerDao.findById(customerId).orElseThrow();
	    	List<OrderItemResponseDTO> orderDtoItems=new ArrayList<OrderItemResponseDTO>();
	    	List<Order> orders=orderDao.findOrdersByCustomerId(customerId);
	    	for (Order order : orders) {
				for (OrderItem orderItem : order.getOrderItems()) {
					OrderItemResponseDTO orderItemResponseDto=new OrderItemResponseDTO();
					orderItemResponseDto.setAmount(orderItem.getPrice());
					orderItemResponseDto.setProductId(orderItem.getProduct().getId());
					orderItemResponseDto.setProductImgUrl(orderItem.getProduct().getMainImgUrl());
					orderItemResponseDto.setProductName(orderItem.getProduct().getName());
					orderItemResponseDto.setQuantity(orderItem.getQuantity());
					orderItemResponseDto.setStatus(order.getStatus().toString());
					orderItemResponseDto.setShippingAddress(modelMapper.map(order.getShippingAddress(), AddressDTO.class));
					orderDtoItems.add(orderItemResponseDto);
				}
			}
	        return orderDtoItems;
	    }

	    public List<OrderItemResponseDTO> getOrderItemsBySeller(Long sellerId) {
	    	List<OrderItem> orderItems=orderItemDao.findOrdersBySellerId(sellerId);
	    	List<OrderItemResponseDTO> orderDtoItems=new ArrayList<OrderItemResponseDTO>();
	    	for (OrderItem orderItem : orderItems) {
	    		OrderItemResponseDTO orderItemResponseDto=new OrderItemResponseDTO();
				orderItemResponseDto.setAmount(orderItem.getPrice());
				orderItemResponseDto.setProductId(orderItem.getProduct().getId());
				orderItemResponseDto.setProductImgUrl(orderItem.getProduct().getMainImgUrl());
				orderItemResponseDto.setProductName(orderItem.getProduct().getName());
				orderItemResponseDto.setQuantity(orderItem.getQuantity());
				orderItemResponseDto.setStatus(orderItem.getOrder().getStatus().toString());
				orderItemResponseDto.setShippingAddress(modelMapper.map(orderItem.getOrder().getShippingAddress(), AddressDTO.class));
				orderDtoItems.add(orderItemResponseDto);
			}
	    	
	        return orderDtoItems;
	    }

	    public Payment addPayment(Payment payment) {
	        return paymentDao.save(payment);
	    }
}	
