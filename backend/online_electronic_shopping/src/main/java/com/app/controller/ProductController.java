package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.CustomerDTO;
import com.app.dto.CustomerUpdateDTO;
import com.app.dto.ProductRequestDTO;
import com.app.dto.ProductResponseDTO;
import com.app.dto.ProductUpdateDTO;
import com.app.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping("/{sellerId}/{categoryId}")
	public ResponseEntity<?> addNewProduct(@ModelAttribute ProductRequestDTO productDto, @PathVariable Long sellerId,
			@PathVariable Long categoryId) throws IOException {

		System.out.println("In add new product: " + productDto);
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(productService.addProduct(productDto, sellerId, categoryId));
		} catch (RuntimeException e) {
			System.out.println("Error: " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchProducts(@RequestParam String query) {
		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(productService.searchProducts(query));
		} catch (RuntimeException e) {
			System.out.println("error" + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	@GetMapping("/all")
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
		List<ProductResponseDTO> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateDTO product, @PathVariable Long productId) {
		System.out.println("in update existing " + product);

		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(product, productId));
		} catch (RuntimeException e) {
			System.out.println("error" + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long sellerId, @PathVariable Long categoryId,
			@PathVariable Long productId) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(productService.deleteProductById(sellerId, categoryId, productId));
		} catch (RuntimeException e) {
			System.out.println("error " + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));

		}
	}
	
	@GetMapping("/getProductById/{productId}")
	public ResponseEntity<?> getProductbyId(@PathVariable Long productId) {
		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(productService.getProductBYId(productId));
		} catch (RuntimeException e) {
			System.out.println("error" + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/getProductsByCategory")
	public ResponseEntity<?> getProductsByCategory(@RequestParam Long categoryId) {
		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(productService.findProductsByCategory(categoryId));
		} catch (RuntimeException e) {
			System.out.println("error" + e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	
	 @GetMapping("/seller/{sellerId}")
	    public ResponseEntity<?> getProductsBySeller(@PathVariable Long sellerId) {
		 System.out.println(sellerId);
	        try {
	            return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsBySeller(sellerId));
	        } catch (RuntimeException e) {
	            System.out.println("Error: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
	        }
	    }
	
	
	
	
	
	
	
	
}
