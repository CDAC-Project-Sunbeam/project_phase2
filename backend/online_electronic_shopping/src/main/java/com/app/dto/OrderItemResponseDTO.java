package com.app.dto;

import com.app.entities.Address;

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

public class OrderItemResponseDTO {
	private String productImgUrl;
	private String productName;
	private double amount;
	private String status;
	private Long productId;
	private int quantity;
	private AddressDTO shippingAddress;
}
