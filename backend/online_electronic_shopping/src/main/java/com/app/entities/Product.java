package com.app.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Product extends BaseEntity{

	 	@Column(nullable = false)
	    private String name;
	    
	    @Column(length = 500)
	    private String description;
	    
	    @Column(nullable = false)
	    private double price;
	    
	    @Column(nullable = false)
	    private int stockQuantity;
	    
	    private double discount;
	    
	    @Column(length = 50,name="brand_name")
	    private String brandName;
	    
	    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private Set<CartItem> cartItems;
	    

	    @ManyToOne
	    @JoinColumn(name = "seller_id", nullable = false)
	    private Seller seller;
	    
	    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private Set<WishlistItem> wishlistItems;
	    
	    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	    private List<ProductImage> images = new ArrayList<>();
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "category_id", nullable = false)
	    private Category category;
}
