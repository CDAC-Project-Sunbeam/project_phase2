package com.app.service;

import java.util.Optional;

import com.app.dto.AddressDTO;
import com.app.dto.SellerDTO;
import com.app.entities.Seller;

public interface SellerService {
	public Seller addSeller(SellerDTO sellerDTO);
	public SellerDTO getSellerDetails(Long sellerid);
	public String updateSellerAddress(Long id,AddressDTO addressDTO);
}
