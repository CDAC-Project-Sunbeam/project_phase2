package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerDao;
import com.app.dao.ProductDao;
import com.app.dao.WishlistDao;
import com.app.dao.WishlistItemDao;
import com.app.dto.ProductResponseDTO;
import com.app.entities.Customer;
import com.app.entities.Product;
import com.app.entities.Wishlist;
import com.app.entities.WishlistItem;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	WishlistDao wishlistDao;
	
	@Autowired
	WishlistItemDao wishlistItemDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String addNewItem(Long customerId,Long productId) {
		System.out.println("in service");
		Product product=productDao.findById(productId).orElseThrow();
		Customer customer=customerDao.findById(customerId).orElseThrow();

		Wishlist wishlist = customer.getWishlist();
        if (wishlist == null) {
            wishlist = new Wishlist();
            customer.setWishlist(wishlist);
            wishlist.setCustomer(customer); // Set the back-reference
        }
        for (WishlistItem item : wishlist.getWishlistItems()) {
            if (item.getProduct().equals(product)) {
                return "Product already in wishlist";
            }
        }

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(product);
        wishlist.addNewItem(wishlistItem);

        wishlistDao.save(wishlist);
       // wishlistItemDao.save(wishlistItem);
		
		
		return "item added in wishlist";
	}
	@Override
	public List<ProductResponseDTO> getWishlistProducts(Long customerId) {
	   
	    Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

	    
	    Wishlist wishlist = customer.getWishlist();

	    
	    if (wishlist == null) {
	        return new ArrayList<>();
	    }

	    
	    List<ProductResponseDTO> productDTOs = new ArrayList<>();

	    
	    for (WishlistItem wishlistItem : wishlist.getWishlistItems()) {
	       
	        Product product = wishlistItem.getProduct();
	        
	        
	        ProductResponseDTO productDTO = modelMapper.map(product, ProductResponseDTO.class);
	        
	        
	        productDTOs.add(productDTO);
	    }

	   
	    return productDTOs;
	}
	
	@Override
	public String removeProduct(Long productId, Long customerId) {
	    
	    Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

	    
	    Wishlist wishlist = customer.getWishlist();
	    if (wishlist == null) {
	        throw new RuntimeException("Wishlist not found");
	    }

	   
	    WishlistItem itemToDelete = null;
	    for (WishlistItem wishlistItem : wishlist.getWishlistItems()) {
	        if (wishlistItem.getProduct().getId().equals(productId)) {
	            itemToDelete = wishlistItem;
	            break;
	        }
	    }

	    
	    if (itemToDelete != null) {
	        wishlist.getWishlistItems().remove(itemToDelete);  
	        wishlistItemDao.deleteById(itemToDelete.getId());  
	    } else {
	        throw new RuntimeException("Product not found in wishlist");
	    }

	    return "Item deleted";
	}

				
}
