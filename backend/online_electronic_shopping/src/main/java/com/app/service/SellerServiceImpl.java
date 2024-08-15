package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerDao;
import com.app.dao.SellerDao;
import com.app.dto.AddressDTO;
import com.app.dto.CustomerDTO;
import com.app.dto.SellerDTO;
import com.app.entities.Address;
import com.app.entities.Customer;
import com.app.entities.Role;
import com.app.entities.Seller;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private ModelMapper modelMapper;
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	@Override
	public Seller addSeller(SellerDTO sellerDTO) {
		// TODO Auto-generated method stub
		Seller seller = modelMapper.map(sellerDTO, Seller.class);
		seller.setPassword(passwordEncoder.encode(seller.getPassword()));
		seller.setRole(Role.valueOf("SELLER"));
		return sellerDao.save(seller);
		
	}
	@Override
	public SellerDTO getSellerDetails(Long sellerid) {
		// TODO Auto-generated method stub
		Seller seller=sellerDao.findById(sellerid).orElseThrow();
		SellerDTO sellerDTO = modelMapper.map(seller, SellerDTO.class);
		return sellerDTO;
	}
	@Override
	public String updateSellerAddress(Long id,AddressDTO addressDTO) {
		Address address = modelMapper.map(addressDTO, Address.class);
		Seller seller=sellerDao.findById(id).orElseThrow();
		seller.setSellerAddress(address);
		
		
		return "added";
	}
}
