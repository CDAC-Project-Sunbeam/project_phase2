package com.sunbeam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class CategoryDTO extends BaseDTO {
	private String categoryName;
	private String description;
}
