package com.sunbeam.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.sunbeam.custom_exceptions.ApiException;
import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dao.CategoryDao;
import com.sunbeam.dto.CategoryDTO;
import com.sunbeam.dto.CategoryPostDTO;
import com.sunbeam.entities.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	// depcy - dao
	@Autowired
	private CategoryDao categoryDao;
	// depcy - model mapper
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public Category saveCategoryDetails(Category category) {
		// validate if category already exists by the supplied name : B.L validation
		if (categoryDao.existsByCategoryName(category.getCategoryName()))
			throw new ApiException("Category name duplicate !!!!!");
		// => category name - unique
		return categoryDao.save(category);
	}// session.flush-> insert -> close

	@Override
	public CategoryDTO getCategoryDetails(Long categoryId) {
		// TODO Auto-generated method stub
		Category categoryEntity = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category Id !!!!"));
		// entity -> dto
		return modelMapper.map(categoryEntity, CategoryDTO.class);
	}

	@Override
	public String updateCategoryDetails(Long categoryId, Category category) {
		// validate if category exists !
		if (categoryDao.existsById(categoryId)) {
			// => exists
			Category persistentCategory = categoryDao.save(category);
			return "Category updated.....";
		}
		throw new ResourceNotFoundException("Invalid Category ID !!!!!!!!");
	}

	@Override
	public String deleteCategoryDetails(Long categoryId) {
		if (categoryDao.existsById(categoryId)) {
			categoryDao.deleteById(categoryId);
			return "deleted category !!!";
		}
		throw new ResourceNotFoundException("Invalid category id !!!");
	}

	@Override
	public CategoryPostDTO getCategoryAndPostsDetails
	(Long categoryId) {
		// get category + post details
		Category category=categoryDao.listCategoryAndPosts(categoryId)
				.orElseThrow(() -> 
				new ResourceNotFoundException("Invalid category id !!!!"));
		//map entity -> dto		
		return modelMapper.map(category, CategoryPostDTO.class);
	}
	
	

}
