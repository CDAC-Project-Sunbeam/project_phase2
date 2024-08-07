package com.sunbeam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude = {"blogCategory","blogger"} )
@EqualsAndHashCode(of="title",callSuper = false)
public class BlogPost extends BaseEntity {
	@Column(length = 30, unique = true)
	private String title;
	private String description;
	private String content;
	// many -> one BlogPost * -> 1 Category
	@ManyToOne (fetch = FetchType.LAZY)// mandatory
	@JoinColumn(name = "category_id", nullable = false)
	private Category blogCategory;
	// many -> one , BlogPost *----> User(Blogger)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id" , nullable = false )
	private User blogger;


	public BlogPost(String title, String description, String content) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
	}

	}
