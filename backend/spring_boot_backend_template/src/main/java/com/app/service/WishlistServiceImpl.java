package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerDao;
import com.app.dao.ProductDao;
import com.app.dao.WishlistDao;
import com.app.dao.WishlistItemDao;
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

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setProduct(product);
        wishlist.addNewItem(wishlistItem);

        wishlistDao.save(wishlist);
        wishlistItemDao.save(wishlistItem);
		
		
		return "item added in wishlist";
	}
				
}
