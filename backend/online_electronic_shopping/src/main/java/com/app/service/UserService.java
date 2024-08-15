package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.SignInRequest;
import com.app.dto.UserDTO;
import com.app.entities.User;


public interface UserService {
	User addUser(UserDTO user);

	public Optional<User> getUserDetails(Long categoryId) ;
	public List<User> getAllCustomers();
	public UserDTO authenticateUser(SignInRequest request);
	
}
