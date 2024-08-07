package com.sunbeam.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {
	@Column(length = 20, unique = true)
	private String name;
	//Tag *---->* BlogPost
	@ManyToMany //mandatory
	@JoinTable(name="my_tag_posts",
	joinColumns = @JoinColumn(name="tag_id"),
	inverseJoinColumns = @JoinColumn(name="post_id"))	
	private Set<BlogPost> posts=new HashSet<>();//def size=0, init capa=16 , L.F =.75

	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public Tag(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	
	public Set<BlogPost> getPosts() {
		return posts;
	}

	public void setPosts(Set<BlogPost> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Tag [name=" + name + "]";
	}

}
