package com.sunbeam.service;

import java.util.List;

import com.sunbeam.dto.CategoryDTO;
import com.sunbeam.dto.CategoryPostDTO;
import com.sunbeam.entities.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	Category saveCategoryDetails(Category category);
	CategoryDTO getCategoryDetails(Long categoryId);
	String updateCategoryDetails(Long categoryId,Category category);
	String deleteCategoryDetails(Long categoryId);
	//add a method to get category + post details , by category id
	CategoryPostDTO getCategoryAndPostsDetails(Long categoryId);
	
}
