package com.sunbeam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.sunbeam.custom_exceptions.ResourceNotFoundException;
import com.sunbeam.dao.BlogPostDao;
import com.sunbeam.dao.CategoryDao;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.BlogPostDTO;
import com.sunbeam.entities.BlogPost;
import com.sunbeam.entities.Category;
import com.sunbeam.entities.User;

@Service
@Transactional
public class BlogPostServiceImpl implements BlogPostService {
	// depcy - blog post dao
	@Autowired
	private BlogPostDao blogPostDao;

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addNewBlog(BlogPostDTO dto) {
		// 1. get category from it's id
		Category category = categoryDao.findById(dto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category ID !"));
		//2. get blogger from it's id
		User blogger=userDao.findById(dto.getBloggerId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Blogger id ID !"));
		//=> category n blogger exists
		//map dto -> entity
		BlogPost blogPostEntity = modelMapper.map(dto, BlogPost.class);
		//3.establish bi dir category 1<-->* blogpost
		category.addBlogPost(blogPostEntity);
		//4. establish uni dir blogpost* ->1 blogger
		blogPostEntity.setBlogger(blogger);
	//	blogPostDao.save(blogPostEntity);NOT required : thanks to cascadeType.ALL
		return new ApiResponse("Added new blog post");
	}

}
