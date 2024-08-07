package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Address;
import com.app.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO extends UserDTO{
	@JsonProperty(access = Access.READ_ONLY)
	private Address customerAddress;
    
    
    
}
