package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.CustomerUpdateDTO;
import com.app.dto.ProductRequestDTO;
import com.app.dto.ProductResponseDTO;
import com.app.dto.ProductUpdateDTO;
import com.app.entities.Product;

public interface ProductService {
	public ProductResponseDTO addProduct(ProductRequestDTO productDto, Long sellerId, Long categoryId) throws IOException  ;
	public String deleteProductById(Long productId,Long sellerId,Long categoryId);
	public String updateProduct(ProductUpdateDTO product , Long id);
	public List<ProductResponseDTO> getAllProducts();
	public List<ProductResponseDTO> searchProducts(String searchTerm);
	public ProductResponseDTO getProductBYId(Long id);
	public List<ProductResponseDTO> findProductsByCategory(Long categoryId);

}
