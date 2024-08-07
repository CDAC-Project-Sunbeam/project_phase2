package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.BlogPostDTO;
import com.sunbeam.service.BlogPostService;



@RestController
@RequestMapping("/posts")
public class BlogPostController {
	@Autowired
	private BlogPostService blogPostService;
	
	/*
	 * Desc - Add new Blog Post
	 *  URL - http://host:port/posts
	 * Method - POST 
	 * payload - BlogPostDTO (category Id , author id , title content , desc) 
	 * Successful Resp - SC 201 Category + mesg (ApiResponse)
	 * Error resp - SC 400 , error mesg -wrapped in DTO(ApiResponse)

	 */
	@PostMapping
	public ResponseEntity<?> addNewBlog(@RequestBody BlogPostDTO dto)
	{
		System.out.println("in add post "+dto);		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(blogPostService.addNewBlog(dto));		
	}


}
