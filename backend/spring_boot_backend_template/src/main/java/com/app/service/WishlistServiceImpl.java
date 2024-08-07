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
//		Wishlist wishlist=customer.getWishlist();
//	
//		wishlist.setCustomer(customer);
//		WishlistItem wishlistItem=new WishlistItem();
//		wishlistItem.setProduct(product);
//		wishlist.addNewItem(wishlistItem);
//		
//		wishlistDao.save(wishlist);
//		wishlistItemDao.save(wishlistItem);
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
	    // Fetch the customer by ID, throw an exception if not found
	    Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

	    // Get the customer's wishlist
	    Wishlist wishlist = customer.getWishlist();

	    // If the wishlist is null, return an empty list
	    if (wishlist == null) {
	        return new ArrayList<>();
	    }

	    // Create a list to hold ProductDTOs
	    List<ProductResponseDTO> productDTOs = new ArrayList<>();

	    // Iterate over each WishlistItem in the wishlist
	    for (WishlistItem wishlistItem : wishlist.getWishlistItems()) {
	        // Get the product from the wishlist item
	        Product product = wishlistItem.getProduct();
	        
	        // Map the product to a ProductDTO
	        ProductResponseDTO productDTO = modelMapper.map(product, ProductResponseDTO.class);
	        
	        // Add the ProductDTO to the list
	        productDTOs.add(productDTO);
	    }

	    // Return the list of ProductDTOs
	    return productDTOs;
	}
	
	@Override
	public String removeProduct(Long productId, Long customerId) {
	    // Fetch the customer by ID, throw an exception if not found
	    Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

	    // Get the customer's wishlist
	    Wishlist wishlist = customer.getWishlist();
	    if (wishlist == null) {
	        throw new RuntimeException("Wishlist not found");
	    }

	    // Find the WishlistItem to delete
	    WishlistItem itemToDelete = null;
	    for (WishlistItem wishlistItem : wishlist.getWishlistItems()) {
	        if (wishlistItem.getProduct().getId().equals(productId)) {
	            itemToDelete = wishlistItem;
	            break;
	        }
	    }

	    // If the item was found, remove it from the wishlist and delete it from the database
	    if (itemToDelete != null) {
	        wishlist.getWishlistItems().remove(itemToDelete);  // Remove from the collection
	        wishlistItemDao.deleteById(itemToDelete.getId());              // Delete from the database
	    } else {
	        throw new RuntimeException("Product not found in wishlist");
	    }

	    return "Item deleted";
	}

				
}
