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
import com.app.dto.CustomerDTO;
import com.app.dto.ProductRequestDTO;
import com.app.service.ProductService;

@RestController
@RequestMapping("/product/{sellerId}/{categoryId}")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@PostMapping
	public ResponseEntity<?> addNewProduct(@RequestBody ProductRequestDTO productDto,@PathVariable Long sellerId,@PathVariable Long categoryId){
		System.out.println("in add new product"+productDto);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productDto, sellerId,categoryId));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}
}
