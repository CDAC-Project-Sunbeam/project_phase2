package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
public class Category {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long categoryId;

	    @Column(nullable = false, unique = true)
	    private String name;

	    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Product> products = new ArrayList<>();
	    
	    public void addNewProduct(Product product) {
			//add post ref to the list
			this.products.add(product);
			//assign category ref to the post
			product.setCategory(this);
		}
}
