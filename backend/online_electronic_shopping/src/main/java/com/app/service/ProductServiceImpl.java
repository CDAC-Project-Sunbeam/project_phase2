package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryDao;
import com.app.dao.ProductDao;
import com.app.dao.SellerDao;
import com.app.dto.ProductRequestDTO;
import com.app.dto.ProductResponseDTO;
import com.app.dto.ProductUpdateDTO;
import com.app.entities.Category;
import com.app.entities.Customer;
import com.app.entities.Product;
import com.app.entities.Seller;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;
	
	
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	SellerDao sellerDao;
	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO productDto,Long sellerId,Long categoryId) {
		Product product =modelMapper.map(productDto, Product.class);
		System.out.println(product);
		Seller seller=sellerDao.findById(sellerId).orElseThrow();
		
		
		Category category=categoryDao.findById(categoryId).orElseThrow();
		
		
		category.addNewProduct(product);
		System.out.println(product);
		product.setSeller(seller);
		
		System.out.println(product);
		productDao.save(product);
		System.out.println(product);
		ProductResponseDTO productResponseDto= modelMapper.map(product, ProductResponseDTO.class);
		productResponseDto.setCategoryName(category.getName());
		productResponseDto.setSellerBusinessName(seller.getBusinessName());
		return productResponseDto;
	}
	@Override
	public String deleteProductById(Long productId,Long sellerId,Long categoryId) {
		
		  System.out.println("Attempting to delete product with ID: " + productId);
		    System.out.println("Seller ID: " + sellerId);
		    System.out.println("Category ID: " + categoryId);
		Product product = productDao.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
		Seller seller = sellerDao.findById(sellerId).orElseThrow(()-> new RuntimeException("seller not found"));
		Category category = categoryDao.findById(categoryId).orElseThrow(()-> new RuntimeException("category not found"));

		if(product.getSeller().getId().equals(seller.getId()) &&product.getCategory().getCategoryId().equals(category.getCategoryId())){
		productDao.deleteById(productId);
		
		}
		return "Product deleted";
		
		
	}
	@Override
	public String updateProduct(ProductUpdateDTO product, Long id) {
	if (productDao.existsById(id)) {
			
			Product product1=productDao.findById(id).orElseThrow();
			product1.setName(product.getName());
			product1.setDescription(product.getDescription());
			product1.setPrice(product.getPrice());
			product1.setStockQuantity(product.getStockQuantity());
			product1.setDiscount(product.getDiscount());
			product1.setBrandName(product.getBrandName());
			product1.setMainImgUrl(product.getMainImgUrl());
			Product updatedProduct = productDao.save(product1);
			System.out.println(product1);
			return "product updated.....";
		}
 
			return "not updated";
	}
	
}
