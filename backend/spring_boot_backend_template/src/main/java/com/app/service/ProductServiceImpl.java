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
import com.app.entities.Category;
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
	
}
