package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AllOrdersForAdminDTO {
    private Long id;
	private String productImgUrl;
	private String productName;
	private double amount;
	private String status;
	private Long productId;
	private int quantity;
	private AddressDTO shippingAddress;

	
	
	
	
	
	
	
}
