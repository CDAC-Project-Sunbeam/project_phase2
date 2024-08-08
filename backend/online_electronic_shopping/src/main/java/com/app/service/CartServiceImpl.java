package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartDao;
import com.app.dao.CartItemDao;
import com.app.dao.CustomerDao;
import com.app.dao.ProductDao;
import com.app.dto.CartProductDTO;
import com.app.dto.ProductResponseDTO;
import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Customer;
import com.app.entities.Product;
import com.app.entities.Wishlist;
import com.app.entities.WishlistItem;



@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	CartItemDao cartItemDao;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public String addNewItem(Long customerId,Long productId,Integer quantity) {
		System.out.println("in cart service");
		System.out.println(quantity);
		Product product=productDao.findById(productId).orElseThrow();
		Customer customer=customerDao.findById(customerId).orElseThrow();

		Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
            customer.setCart(cart);
            cart.setCustomer(customer); // Set the back-reference
        }
        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().equals(product)) {
                return "Product already in cart";
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cart.addNewItem(cartItem);

        cartDao.save(cart);	
		return "item added in cart";
	}
	
	@Override
	public List<CartProductDTO> getCartProducts(Long customerId) {
	    // Fetch the customer by ID, throw an exception if not found
	    Customer customer = customerDao.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

	    // Get the customer's wishlist
	    Cart cart = customer.getCart();

	    // If the wishlist is null, return an empty list
	    if (cart == null) {
	        return new ArrayList<>();
	    }

	    // Create a list to hold ProductDTOs
	    List<CartProductDTO> productDTOs = new ArrayList<>();

	    // Iterate over each WishlistItem in the wishlist
	    for (CartItem cartItem : cart.getCartItems()) {
	        // Get the product from the wishlist item
	        Product product = cartItem.getProduct();
	        
	        // Map the product to a ProductDTO
	        CartProductDTO productDTO = modelMapper.map(product, CartProductDTO.class);
	        productDTO.setQuantity(cartItem.getQuantity());
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
	    Cart cart = customer.getCart();
	    if (cart == null) {
	        throw new RuntimeException("cart not found");
	    }

	    // Find the WishlistItem to delete
	    CartItem itemToDelete = null;
	    for (CartItem cartItem : cart.getCartItems()) {
	        if (cartItem.getProduct().getId().equals(productId)) {
	            itemToDelete = cartItem;
	            break;
	        }
	    }

	    // If the item was found, remove it from the wishlist and delete it from the database
	    if (itemToDelete != null) {
	        cart.getCartItems().remove(itemToDelete);  // Remove from the collection
	        cartItemDao.deleteById(itemToDelete.getId());              // Delete from the database
	    } else {
	        throw new RuntimeException("Product not found in cart");
	    }

	    return "Item deleted";
	}
}
