package com.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CustomerDTO;
import com.app.dto.UserDTO;
import com.app.entities.User;
import com.app.service.CustomerService;
import com.app.service.UserService;
import com.app.dto.SignInRequest;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody 
			@Valid SignInRequest request) {
		System.out.println("in sign in " + request);
		
			return ResponseEntity
					.ok(userService.authenticateUser(request));
		
	}
	@GetMapping("")
	public ResponseEntity<?> getCustomers(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAllCustomers());
		}
		catch(RuntimeException e){
			System.out.println("error"+e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
}
