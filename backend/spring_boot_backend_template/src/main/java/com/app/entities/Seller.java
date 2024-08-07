package com.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "sellers")
@Getter
@Setter
@NoArgsConstructor
public class Seller extends User{
	 	
	   
	    @Column(name="buisness_name")
	    private String businessName;
	    @Column(nullable = true)
	    private String taxId;
	    
	    @OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	    @JoinColumn(name="seller_address_id")
	    private Address sellerAddress;
}
