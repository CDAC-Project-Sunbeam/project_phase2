package com.app.service;

import java.util.Optional;

import com.app.dto.AddressDTO;
import com.app.dto.CustomerDTO;
import com.app.dto.CustomerUpdateDTO;
import com.app.dto.UserDTO;
import com.app.entities.Address;
import com.app.entities.Customer;
import com.app.entities.User;

public interface CustomerService {
	Customer addCustomer(CustomerDTO customer);
	public Optional<Customer> getCustomerDetails(Long userId);
	public String addCustomerAddress(Long id,AddressDTO addressDTO);
	public String updateCustomer(CustomerUpdateDTO customer , Long id);
	public Address updateCustomerAddress(AddressDTO address , Long id) ;
}
