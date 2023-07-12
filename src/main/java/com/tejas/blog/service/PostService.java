package com.tejas.blog.service;

import java.util.List;

import com.tejas.blog.entities.Post;
import com.tejas.blog.payloads.PostDto;
import com.tejas.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber ,Integer pagesize, String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
	
	
	
	
	
	
}
