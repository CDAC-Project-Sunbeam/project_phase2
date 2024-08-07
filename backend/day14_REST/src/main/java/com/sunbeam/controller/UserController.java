package com.sunbeam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.AddressDTO;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.SignInRequest;
import com.sunbeam.service.AddressService;
import com.sunbeam.service.UserService;

@RestController
@RequestMapping("/users")

public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	
	

	/*
	 * Objective : User signin endpoint URL : http://host:port/users/signin Method :
	 * POST (for adding email n pwd : in request body as additional security.) Desc
	 * - User signin URL - http://host:port/users/signin Method - POST payload - dto
	 * (email n pwd) Successful Resp - SC 200, user details - all (dto) Error resp -
	 * SC 400 , error mesg -wrapped in DTO(ApiResponse)
	 * 
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody 
			@Valid SignInRequest request) {
		System.out.println("in sign in " + request);
		
			return ResponseEntity
					.ok(userService.authenticateUser(request));
		
	}
	//assign user address
	@PostMapping("/address")
	public ResponseEntity<?> assignUserAddress(@RequestBody AddressDTO dto){
		System.out.println("in assign adr "+dto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(addressService.linkUserAddress(dto));
	}

}
