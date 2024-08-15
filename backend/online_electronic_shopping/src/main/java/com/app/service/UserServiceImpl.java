package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.AuthenticationException;
import com.app.dao.UserDao;
import com.app.dto.UserDTO;
import com.app.entities.Role;
import com.app.entities.User;

import com.app.dto.CustomerDTO;
import com.app.dto.SellerDTO;
import com.app.dto.SignInRequest;



@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserDao userDao;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	@Override
	public User addUser(UserDTO userDt) {
		// TODO Auto-generated method stub
		User user = modelMapper.map(userDt, User.class);
		
		return userDao.save(user);
	}
	@Override
	public Optional<User> getUserDetails(Long userId) {
		// TODO Auto-generated method stub
			
		return userDao.findById(userId);
	}
	@Override
	public List<User> getAllCustomers() {
		// TODO Auto-generated method stub
			
		return userDao.findByRole(Role.valueOf("CUSTOMER"));
	}
	@Override
	public UserDTO authenticateUser(SignInRequest request) {
		
		User userEntity = userDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new AuthenticationException("Invalid Email Or Password"));
		
		if(userEntity.getRole()==Role.valueOf("CUSTOMER")) {
			return modelMapper.map(userEntity, CustomerDTO.class);
		}
		else if(userEntity.getRole()==Role.valueOf("SELLER")) {
		return modelMapper.map(userEntity, SellerDTO.class);
		}
		else {
			return modelMapper.map(userEntity, UserDTO.class);
		}
	}
  

}
