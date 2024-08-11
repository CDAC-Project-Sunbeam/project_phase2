package com.app.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductUpdateDTO {
 	
    private String name;
    
    private String description;
    
    private double price;
   
    private int stockQuantity;
    
    private double discount;
    
    private String brandName;
    
    private String mainImgUrl;
}
