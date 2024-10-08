package com.app.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
public class Cart extends BaseEntity{
	    
	    @OneToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    private Customer customer;
	    
	    
	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private Set<CartItem> cartItems=new HashSet<CartItem>();

	    // Getters and Setters
	    public void addNewItem(CartItem cartItem) {
			//add post ref to the list
			this.cartItems.add(cartItem);
			//assign category ref to the post
			cartItem.setCart(this);
		}
}
