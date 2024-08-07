package com.sunbeam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dao.AddressDao;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.AddressDTO;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.entities.Address;
import com.sunbeam.entities.User;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse linkUserAddress(AddressDTO dto) {
		// 1. get user from it's id
		User user = userDao.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid user id !!!"));
		//=> user - PERSISTENT
		//map dto -> entity
		Address addressEntity = modelMapper.map(dto,Address.class);
		
		//3. establish the relationship User ->Address (entity)
		user.setUserAddress(addressEntity);		
		return new ApiResponse("assigned user address");
	}

}
