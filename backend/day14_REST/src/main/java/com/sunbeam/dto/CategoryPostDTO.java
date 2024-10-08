package com.sunbeam.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryPostDTO extends BaseDTO{
	private String categoryName;
	private String description;
	List<BlogPostDTO> posts=new ArrayList<>();
}
