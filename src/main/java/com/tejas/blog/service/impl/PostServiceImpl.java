package com.tejas.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.PerThisOrTargetPointcutVisitor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tejas.blog.entities.Category;
import com.tejas.blog.entities.Post;
import com.tejas.blog.entities.User;
import com.tejas.blog.exceptions.ResourceNotFoundException;
import com.tejas.blog.payloads.PostDto;
import com.tejas.blog.payloads.PostResponse;
import com.tejas.blog.repository.CategoryRepo;
import com.tejas.blog.repository.PostRepo;
import com.tejas.blog.repository.UserRepo;
import com.tejas.blog.service.PostService;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post save = this.postRepo.save(post);
		
		return this.modelMapper.map(save, PostDto.class);
	}
	
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pagesize,String sortBy,String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
		/*
		 * Sort sort=null; if(sortDir.equalsIgnoreCase("asc")) {
		 * sort=Sort.by(sortBy).ascending(); }else { sort=Sort.by(sortBy).descending();
		 * }
		 */
		
		Pageable p= PageRequest.of(pageNumber, pagesize,sort);
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allpost = pagePost.getContent();		
		
		List<PostDto> postDtos = allpost.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPagesize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtos = posts.stream().map((post -> this.modelMapper.map(post, PostDto.class)))
				       .collect(Collectors.toList());
		
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}


	@Override
	public PostDto getPostById(Integer postId) {
		
		 Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		 return this.modelMapper.map(post, PostDto.class);
		 
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatepost = postRepo.save(post);
		return modelMapper.map(updatepost, PostDto.class);
	}


	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}




	

}
