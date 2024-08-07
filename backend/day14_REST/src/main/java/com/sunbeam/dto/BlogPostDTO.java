package com.sunbeam.dto;

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
@ToString(callSuper = true)
public class BlogPostDTO extends BaseDTO {
	private String title;
	private String description;
	private String content;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long bloggerId;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long categoryId;
}
