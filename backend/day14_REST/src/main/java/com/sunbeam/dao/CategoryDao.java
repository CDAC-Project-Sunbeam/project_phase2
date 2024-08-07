package com.sunbeam.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sunbeam.entities.Category;

public interface CategoryDao extends JpaRepository<Category,Long> {
//derived query
	boolean existsByCategoryName(String name);
	//write a custom query to fetch category + posts
	@Query("select c from Category c left join fetch c.posts where c.id=:catId")
	Optional<Category> listCategoryAndPosts(Long catId);
			
}
