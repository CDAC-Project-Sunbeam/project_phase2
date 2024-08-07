package com.sunbeam.controller;

import java.util.List;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.entities.Category;
import com.sunbeam.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryController {
	// depcy
	@Autowired
	private CategoryService categoryService;

	public CategoryController() {
		System.out.println("in ctor " + getClass());
	}

	/*
	 * URL - http://host:port/categories Method - GET payload - none resp - list of
	 * categories
	 */
	@GetMapping
	public ResponseEntity<?> listCategories() {
		System.out.println("in list categories");
		List<Category> list = categoryService.getAllCategories();
		if (list.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(list);
	}

	/*
	 * URL - http://host:port/categories Method - POST payload - json for category
	 * resp - saved category
	 */
	@PostMapping
	public ResponseEntity<?> addNewCategory(@RequestBody Category newCategory) {
		System.out.println("in add new category " + newCategory);

		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategoryDetails(newCategory));
	}

	/*
	 * operation - getting category details by id URL -
	 * http://host:port/categories/{categodryId} - URI template variable Method -GET
	 * payload - none resp - category
	 */
	@GetMapping("/{categoryId}")
	@Operation(description = "Get Category Details By Id")
	public ResponseEntity<?> getCategoryDetails(@PathVariable @Max(100) Long categoryId) {
		System.out.println("in get cat dtls " + categoryId);

		return ResponseEntity.ok(categoryService.getCategoryDetails(categoryId));

	}

	/*
	 * URL - http://host:port/categories/{id} Method - PUT payload - json for
	 * category to be updated resp - DTO (api resp)
	 */
	@PutMapping("/{id}")
	@Operation(description = "Update category details")
	public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable Long id) {
		System.out.println("in update " + id + " " + category);
		try {
			return ResponseEntity.ok(new ApiResponse(categoryService.updateCategoryDetails(id, category)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	/*
	 * URL - http://host:port/categories/{id} Method - DELETE payload -none resp -
	 * DTO
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		System.out.println("in delete " + id);
		try {
			return ResponseEntity.ok(new ApiResponse(categoryService.deleteCategoryDetails(id)));
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}

	}

	/*
	 * operation - getting category +posts details by category id URL -
	 * http://host:port/categories/{categoryId}/posts URI template variable - cat id
	 * Method -GET payload - none resp - category +posts
	 */
	@GetMapping("/{categoryId}/posts")
	public ResponseEntity<?> getCategoryAndPosts(@PathVariable Long categoryId) {
		System.out.println("in cat + posts " + categoryId);
		return ResponseEntity.ok(categoryService.getCategoryAndPostsDetails(categoryId));
	}
}
