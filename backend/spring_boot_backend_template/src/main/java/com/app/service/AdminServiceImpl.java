package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.app.dao.AdminDao;
import com.app.dto.CustomerDTO;
import com.app.dto.SellerDTO;
import com.app.dto.UserDTO;
import com.app.entities.Customer;
import com.app.entities.Role;
import com.app.entities.Seller;
import com.app.entities.User;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
     
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public List<CustomerDTO> getAllCustomers() {
    List<User>	customerslist =	adminDao.findByRole(Role.valueOf("CUSTOMER"));
    List<CustomerDTO> customerDtoList=new ArrayList<CustomerDTO>();
    for (User user : customerslist) {
    	CustomerDTO customerDTO = modelMapper.map(user, CustomerDTO.class);
    	customerDtoList.add(customerDTO);
	}
    
    	return customerDtoList;
	}
	@Override
	public List<SellerDTO> getAllSellers() {
	    List<User>	sellerslist =	adminDao.findByRole(Role.valueOf("SELLER"));
	    List<SellerDTO> sellerDtoList=new ArrayList<SellerDTO>();
	    for (User user : sellerslist) {
	    	SellerDTO sellerDto = modelMapper.map(user, SellerDTO.class);
	    	sellerDtoList.add(sellerDto);
		}
	    
	    return sellerDtoList;
	}
	@Override
	public String blockUser(Long id) {
		User user=adminDao.findById(id).orElseThrow();
		user.setBlocked(true);
		return "blocked";
	}
	
	@Override
	public String UnblockUser(Long id) {
		User user=adminDao.findById(id).orElseThrow();
		user.setBlocked(false);
		return "Unblocked";
	}
}
