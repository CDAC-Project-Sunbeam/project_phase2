package com.sunbeam.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude = "posts")
public class Category extends BaseEntity {
	@Column(name = "category_name", length = 30, unique = true)
	private String categoryName;
	private String description;
	// one -> many Category 1->* BlogPost
//	@JsonIgnore //To tell Jackson to skip this field during ser n de-ser.
	@OneToMany(mappedBy = "blogCategory", 
			cascade = CascadeType.ALL, orphanRemoval = true) // mandatory , otherwise
									// MappingException !
	private List<BlogPost> posts = new ArrayList<>();

	public Category(String categoryName, String description) {
		super();
		this.categoryName = categoryName;
		this.description = description;
	}

	// add helper method to establish a bi dir association between Category 1<--->*
	// BlogPost
	public void addBlogPost(BlogPost post) {
		//add post ref to the list
		this.posts.add(post);
		//assign category ref to the post
		post.setBlogCategory(this);
	}
	// add helper method to un -establish(de link) a bi dir association between Category 1<--->*
		// BlogPost
		public void removeBlogPost(BlogPost post) {
			this.posts.remove(post);
			post.setBlogCategory(null);
		}
}
