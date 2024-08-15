package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.OrderRequestDTO;
import com.app.entities.Customer;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.Payment;
import com.app.entities.Seller;
import com.app.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins="http://localhost:3000")
public class OrderController {
		@Autowired
	    private OrderService orderService;

	    @PostMapping("/createOrder/{customerId}")
	    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDto,@PathVariable Long customerId) {
	        
	        try {
				return ResponseEntity.status(HttpStatus.OK).body(orderService.createOrder(orderRequestDto,customerId));
			}
			catch(RuntimeException e){
				System.out.println("error"+e);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	    }
	    
	    @GetMapping("/customer/{customerId}")
	    public ResponseEntity<?> getOrdersByCustomer(@PathVariable Long customerId) {
	        try {
				return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByCustomer(customerId));
			}
			catch(RuntimeException e){
				System.out.println("error"+e);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	    }
	    @GetMapping("/seller/{sellerId}")
	    public ResponseEntity<?> getOrdersBySellers(@PathVariable Long sellerId) {
	        try {
				return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderItemsBySeller(sellerId));
			}
			catch(RuntimeException e){
				System.out.println("error"+e);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	    }
	    @GetMapping("/all")
	    public ResponseEntity<?> getAllOrders() {
	        try {
				return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
			}
			catch(RuntimeException e){
				System.out.println("error"+e);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	    }
	    
	    @GetMapping("/allForAdmin")
	    public ResponseEntity<?> getAllOrdersForAdmin() {
	        try {
				return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrdersForAdmin());
			}
			catch(RuntimeException e){
				System.out.println("error"+e);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			}
	    }
	    

	    @PostMapping("/{orderId}/payment")
	    public ResponseEntity<Payment> addPayment(@PathVariable Long orderId, @RequestBody Payment payment) {
	        Order order = new Order(); // retrieve order entity based on orderId
	        order.setId(orderId);
	        payment.setOrder(order);
	        Payment createdPayment = orderService.addPayment(payment);
	        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
	    }

}
