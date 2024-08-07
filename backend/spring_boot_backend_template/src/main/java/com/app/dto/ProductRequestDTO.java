package com.app.dto;

import javax.persistence.Column;

import org.hibernate.internal.build.AllowPrintStacktrace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
	
    private String name;
    
   
    private String description;
    
 
    private double price;
    
    private String brandName;
    
    private int stockQuantity;
    
    private double discount;
    
}
