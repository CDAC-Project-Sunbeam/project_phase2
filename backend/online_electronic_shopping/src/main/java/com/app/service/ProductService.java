package com.app.service;

import com.app.dto.ProductRequestDTO;
import com.app.dto.ProductResponseDTO;
import com.app.entities.Product;

public interface ProductService {
	public ProductResponseDTO addProduct(ProductRequestDTO productDto,Long sellerId,Long categoryId);
}
