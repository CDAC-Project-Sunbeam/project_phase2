package com.sunbeam.dto;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {
	private Long userId;

	private String adrLine1;

	private String adrLine2;

	private String city;

	private String state;

	private String country;

	private String zipCode;

}
