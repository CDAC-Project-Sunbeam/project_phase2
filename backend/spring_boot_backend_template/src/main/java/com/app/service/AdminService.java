package com.app.service;

import java.util.List;

import com.app.dto.CustomerDTO;
import com.app.dto.SellerDTO;
import com.app.dto.UserDTO;
import com.app.entities.User;

public interface AdminService {
	public List<CustomerDTO> getAllCustomers();
	public List<SellerDTO> getAllSellers();
	public String blockUser(Long id);
	public String UnblockUser(Long id);
}
