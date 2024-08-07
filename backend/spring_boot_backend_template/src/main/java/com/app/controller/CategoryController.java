package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Category;
import com.app.service.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<?> addNewCategory(@RequestBody String name) {
		System.out.println("in add new category " + name);

		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategoryDetails(name));
	}
}
