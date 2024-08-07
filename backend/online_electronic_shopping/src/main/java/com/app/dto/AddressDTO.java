package com.app.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import com.app.entities.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	
	private String adrLine1;
	
	private String adrLine2;
	
	private String city;

	private String state;
	
	private String country;
	
	private String zipCode;
}
