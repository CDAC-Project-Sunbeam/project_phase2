package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddCartItemRequestDTO;
import com.app.dto.ApiResponse;
import com.app.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	@PostMapping()
	public ResponseEntity<?> addNewItem(@RequestBody AddCartItemRequestDTO addItemDto){
		System.out.println("in add new item"+addItemDto);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addNewItem(addItemDto.getCustomerId(),addItemDto.getProductId(),addItemDto.getQuantity()));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@GetMapping("/{customerId}")
	public ResponseEntity<?> getAllProducts(@PathVariable Long customerId){
		System.out.println("in get all products in cart");
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(cartService.getCartProducts(customerId));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@DeleteMapping("/{customerId}/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long customerId,@PathVariable Long productId){
		System.out.println("in remove product");
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cartService.removeProduct(productId, customerId));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}
	

}
