package com.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.app.security.CustomUserDetails;
import com.app.security.JwtUtils;
import com.app.service.CustomerService;
import com.app.service.UserService;
import com.app.dto.SignInRequest;
import com.app.dto.SigninResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authMgr;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody 
			@Valid SignInRequest request) {
//		System.out.println("in sign in " + request);
//		
//			return ResponseEntity
//					.ok(userService.authenticateUser(request));
//		
//	}
	System.out.println("in sign in" + request);
	//create a token(implementation of Authentication i/f)
	//to store un verified user email n pwd
	UsernamePasswordAuthenticationToken token=new 
			UsernamePasswordAuthenticationToken(request.getEmail(), 
					request.getPassword());
	//invoke auth mgr's authenticate method;
	Authentication verifiedToken = authMgr.authenticate(token);
	//=> authentication n authorization  successful !
	System.out.println(verifiedToken.getPrincipal().getClass());
	   CustomUserDetails customUserDetails = (CustomUserDetails) verifiedToken.getPrincipal();
	    Long userId = customUserDetails.getId();
	//create JWT n send it to the clnt in response
	System.out.println(userId);
	System.out.println("role ="+verifiedToken.getAuthorities());
	SigninResponse resp=new SigninResponse
			(jwtUtils.generateJwtToken(verifiedToken),
			"Successful Auth!!!!",verifiedToken.getAuthorities().toString(),userId);
	
	return ResponseEntity.
			status(HttpStatus.CREATED).body(resp);
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
