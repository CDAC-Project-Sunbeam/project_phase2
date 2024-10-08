package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Category;
import com.app.entities.Product;
import com.app.entities.Seller;

public interface ProductDao extends JpaRepository<Product, Long>{
	 @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.brandName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
	    List<Product> searchProducts(@Param("searchTerm") String searchTerm);
	 	List<Product> findByCategory(Category category);
//	 	List<Product> findBySellerId(Long SellerId);
	 
	 	@Query("SELECT p FROM Product p WHERE p.seller.id = :sellerId")
	 	List<Product> findProductsBySellerId(@Param("sellerId") Long sellerId);

	 	
	 	

}
