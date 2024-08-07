package com.app.service;

import java.util.List;

import com.app.dto.ProductResponseDTO;

public interface WishlistService {
	public String addNewItem(Long customerId,Long productId);
	public List<ProductResponseDTO> getWishlistProducts(Long customerId);
	public String removeProduct(Long productId,Long customerId);
}
