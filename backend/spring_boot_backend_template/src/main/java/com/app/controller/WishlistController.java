package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.SellerDTO;
import com.app.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	@Autowired
	WishlistService wishlistService;
	
	@PostMapping("/{customerId}/{productId}")
	public ResponseEntity<?> addNewItem(@PathVariable Long customerId,@PathVariable Long productId){
		System.out.println("in add new item");
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(wishlistService.addNewItem(customerId, productId));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}

}
