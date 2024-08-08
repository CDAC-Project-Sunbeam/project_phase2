package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartProductDTO {
	
	private Long id;

    private String name;
    
    private double discount;
    
    private String brandName;
    
    private Integer quantity;
    
    private String mainImgUrl;
    

}
