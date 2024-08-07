package com.sunbeam.service;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.BlogPostDTO;

public interface BlogPostService {
//add new blog
	ApiResponse addNewBlog(BlogPostDTO dto);
}
