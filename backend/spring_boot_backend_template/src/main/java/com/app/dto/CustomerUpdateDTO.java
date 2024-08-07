package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class CustomerUpdateDTO {
private String firstName;
    
    
    private String lastName;

    
    private String password;

    
    private String email;
    
    private LocalDate dob;
}
