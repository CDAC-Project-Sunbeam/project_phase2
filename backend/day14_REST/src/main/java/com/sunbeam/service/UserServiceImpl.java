package com.sunbeam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.custom_exceptions.AuthenticationException;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.SignInRequest;
import com.sunbeam.dto.SignInResponse;
import com.sunbeam.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public SignInResponse authenticateUser(SignInRequest request) {
		// invoke dao's method
		User userEntity = userDao.findByEmailAndPassword(request.getEmail(), request.getPassword())
				.orElseThrow(() -> new AuthenticationException("Invalid Email Or Password"));
		// => valid login
		return mapper.map(userEntity, SignInResponse.class);
	}
}
