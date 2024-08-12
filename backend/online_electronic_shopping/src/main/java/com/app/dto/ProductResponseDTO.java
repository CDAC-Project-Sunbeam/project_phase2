package com.app.dto;

import com.app.entities.Category;
import com.app.entities.Seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponseDTO {
	private Long id;

    private String name;
    
   
    private String description;
    
 
    private double price;
    
    private String brandName; 
    
    
    private int stockQuantity;
    
    private double discount;
    
    private String sellerBusinessName;
    
    private String categoryName;
    
    
    private String mainImgUrl;
}
