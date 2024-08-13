package com.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Value("${image.upload.dir}")
    private String uploadDir;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	SellerDao sellerDao;
	
	
	public ProductResponseDTO addProduct(ProductRequestDTO productDto, Long sellerId, Long categoryId) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setBrandName(productDto.getBrandName());
        product.setStockQuantity(productDto.getStockQuantity());
        product.setDiscount(productDto.getDiscount());

        Seller seller = sellerDao.findById(sellerId).orElseThrow();
        Category category = categoryDao.findById(categoryId).orElseThrow();

        category.addNewProduct(product);
        product.setSeller(seller);

        // Handle file upload for the main image
        MultipartFile file = productDto.getFile();
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir, fileName).toAbsolutePath().normalize();
            Files.createDirectories(filePath.getParent()); // Ensure the directory exists
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            product.setMainImgUrl(fileName); // Set the file path in the product entity
        }

        productDao.save(product);

        ProductResponseDTO productResponseDto = modelMapper.map(product, ProductResponseDTO.class);
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
	    public List<ProductResponseDTO> getAllProducts() {
	        List<Product> products = productDao.findAll();
	        return products.stream()
	                       .map(product -> {
	                           ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);
	                           dto.setCategoryName(product.getCategory().getName());
	                           return dto;
	                       })
	                       .collect(Collectors.toList());
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
	
	public List<ProductResponseDTO> searchProducts(String searchTerm) {
        List<Product> products=productDao.searchProducts(searchTerm);
        List<ProductResponseDTO> productDtoList=new ArrayList<ProductResponseDTO>();
        for (Product product : products) {
        	ProductResponseDTO productDto=modelMapper.map(product, ProductResponseDTO.class);
        	productDtoList.add(productDto);
		}
        return productDtoList;
    }
	
	public ProductResponseDTO getProductBYId(Long id) {
	Product product=	productDao.findById(id).orElseThrow();
	ProductResponseDTO productResponseDTO =  modelMapper.map(product, ProductResponseDTO.class);
	productResponseDTO.setCategoryName(product.getCategory().getName());
	return productResponseDTO;
//	return null;
	}
	
	 public List<ProductResponseDTO> findProductsByCategory(Long categoryId) {
	        Category category = categoryDao.findById(categoryId)
	            .orElseThrow(() -> new RuntimeException("Category not found"));
	        List<Product> products=productDao.findByCategory(category);
	        List<ProductResponseDTO> productDtoList=new ArrayList<ProductResponseDTO>();
	        for (Product product : products) {
	        	ProductResponseDTO productDto=modelMapper.map(product, ProductResponseDTO.class);
	        	productDto.setCategoryName(product.getCategory().getName());
	        	productDtoList.add(productDto);
			}
	        return productDtoList;
	 }
	
}
