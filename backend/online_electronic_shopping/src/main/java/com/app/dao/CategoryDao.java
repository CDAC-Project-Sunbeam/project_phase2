package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {
	boolean existsByName(String name);
}
