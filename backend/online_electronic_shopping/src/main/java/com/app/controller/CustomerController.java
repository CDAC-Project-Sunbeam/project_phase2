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
import com.app.dto.CustomerUpdateDTO;
import com.app.entities.Address;
import com.app.entities.Customer;
import com.app.service.CustomerService;
import com.app.service.UserService;
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@PostMapping
	public ResponseEntity<?> addNewUser(@RequestBody CustomerDTO userdto){
		System.out.println("in add new user"+userdto);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(userdto));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerDetails(id));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	@PostMapping("Address/{id}")
	public ResponseEntity<?> addCustomerAdress(@PathVariable Long id,@RequestBody AddressDTO addressDTO){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(customerService.addCustomerAddress(id, addressDTO));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@RequestBody CustomerUpdateDTO customer ,@PathVariable Long id){
		System.out.println("in add new user"+customer);
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomer(customer,id));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
	@PutMapping("/Address/{id}")
	public ResponseEntity<?> updateUserAddress(@RequestBody AddressDTO address ,@PathVariable Long id){
		System.out.println("in add new user"+address);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(customerService.updateCustomerAddress(address,id));
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
		}
	}
}
