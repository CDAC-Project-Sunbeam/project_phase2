package com.app.service;

import com.app.dto.CustomerUpdateDTO;
import com.app.dto.ProductRequestDTO;
import com.app.dto.ProductResponseDTO;
import com.app.dto.ProductUpdateDTO;
import com.app.entities.Product;

public interface ProductService {
	public ProductResponseDTO addProduct(ProductRequestDTO productDto,Long sellerId,Long categoryId);
	public String deleteProductById(Long productId,Long sellerId,Long categoryId);
	public String updateProduct(ProductUpdateDTO product , Long id);

}
