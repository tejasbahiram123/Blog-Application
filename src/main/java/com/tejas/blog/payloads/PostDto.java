package com.tejas.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.tejas.blog.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private Integer postId;

	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();
	
	}
