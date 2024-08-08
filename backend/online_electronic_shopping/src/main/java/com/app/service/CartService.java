package com.app.service;

import java.util.List;

import com.app.dto.CartProductDTO;

public interface CartService {
	public String addNewItem(Long customerId,Long productId,Integer quantity);
	public List<CartProductDTO> getCartProducts(Long customerId);
	public String removeProduct(Long productId, Long customerId);
}
