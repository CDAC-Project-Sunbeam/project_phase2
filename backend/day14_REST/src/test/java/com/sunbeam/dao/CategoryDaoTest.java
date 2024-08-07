package com.sunbeam.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sunbeam.entities.Category;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CategoryDaoTest {
	// depcy - dao layer i/f
	@Autowired
	private CategoryDao categoryDao;

	@Test//method level annotation from JUnit 5 to indicate a method as the test case
	void testListCategoryAndPosts() {
		Category category = categoryDao
				.listCategoryAndPosts(1l).orElseThrow();
		//non standard way of writing a test case !!!!!!!!!!!!
		System.out.println(category);
		category.getPosts().forEach(System.out::println);
		assertEquals(3, category.getPosts().size());
		
	}

}
