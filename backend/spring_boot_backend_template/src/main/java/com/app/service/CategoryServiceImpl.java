package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ApiException;
import com.app.dao.CategoryDao;
import com.app.entities.Category;


@Service
@Transactional

public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public Category addCategoryDetails(String name) {
		// validate if category already exists by the supplied name : B.L validation
		if (categoryDao.existsByName(name)) {
			throw new ApiException("Category name duplicate !!!!!");
		// => category name - unique
		}
		Category category=new Category();
		category.setName(name);
		return categoryDao.save(category);
	}// session.flush-> insert -> close
}
