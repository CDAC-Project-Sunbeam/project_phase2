package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddressDTO;
import com.app.dto.ApiResponse;
import com.app.dto.CustomerDTO;
import com.app.dto.SellerDTO;
import com.app.service.CustomerService;
import com.app.service.SellerService;

@RestController
@RequestMapping("/seller")
public class SellerController {
	@Autowired
	private SellerService sellerService;
	@PostMapping
	public ResponseEntity<?> addNewSeller(@RequestBody SellerDTO sellerdto){
		System.out.println("in add new user"+sellerdto);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.addSeller(sellerdto));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getSeller(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(sellerService.getSellerDetails(id));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	@PutMapping("/Address/{id}")
	public ResponseEntity<?> updateSellerAddress(@PathVariable Long id,@RequestBody AddressDTO addressDTO){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(sellerService.updateSellerAddress(id, addressDTO));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
}
