package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerDao;
import com.app.dao.UserDao;
import com.app.dto.AddressDTO;
import com.app.dto.CustomerDTO;
import com.app.dto.CustomerUpdateDTO;
import com.app.entities.Address;
import com.app.entities.Customer;
import com.app.entities.Role;
import com.app.entities.User;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ModelMapper modelMapper;
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	@Override
	public Customer addCustomer(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customer.setRole(Role.valueOf("CUSTOMER"));
		return customerDao.save(customer);
		
	}
	@Override
	public CustomerDTO getCustomerDetails(Long customerid) {
		// TODO Auto-generated method stub
		CustomerDTO customerDto = modelMapper.map(customerDao.findById(customerid).orElseThrow() , CustomerDTO.class);
		System.out.println(customerDto);
		return customerDto;
	}
	public String addCustomerAddress(Long id,AddressDTO addressDTO) {
		Address address = modelMapper.map(addressDTO, Address.class);
		Customer customer=customerDao.findById(id).orElseThrow();
		customer.setCustomerAddress(address);
		
		
		return "added";
	}
	
	public String updateCustomer(CustomerUpdateDTO customer , Long id) {
		// TODO Auto-generated method stub
		System.out.println(customer);
		
		//Customer customer1 = modelMapper.map(customer , Customer.class);
	
		if (customerDao.existsById(id)) {
			
			Customer customer1=customerDao.findById(id).orElseThrow();
			customer1.setFirstName(customer.getFirstName());
			customer1.setLastName(customer.getLastName());
			customer1.setEmail(customer.getEmail());
			customer1.setPassword(customer.getPassword());
			customer1.setDob(customer.getDob());
			Customer updatedCustomer = customerDao.save(customer1);
			System.out.println(customer1);
			return "customer updated.....";
		}
 
			return "not updated";
	}
	public Address updateCustomerAddress(AddressDTO address , Long id) {
		// TODO Auto-generated method stub
		System.out.println(address);
		
		//Customer customer1 = modelMapper.map(customer , Customer.class);
		Address address1=null;
		if (customerDao.existsById(id)) {
			// => exists
			Customer customer1=customerDao.findById(id).orElseThrow();
				address1 = modelMapper.map(address , Address.class);
				customer1.setCustomerAddress(address1);
			System.out.println(customer1);
			
		}
  
		return address1;
	}
	
}
