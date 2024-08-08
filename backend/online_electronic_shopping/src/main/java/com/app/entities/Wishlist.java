package com.app.entities;

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
@Table(name = "wishlists")
@Getter
@Setter
@NoArgsConstructor
public class Wishlist extends BaseEntity {
	 	@OneToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    private Customer customer;


	    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private Set<WishlistItem> wishlistItems=new HashSet<>();
	    
	    public void addNewItem(WishlistItem wishListItem) {
			
			this.wishlistItems.add(wishListItem);
			
			wishListItem.setWishlist(this);
		}
}
