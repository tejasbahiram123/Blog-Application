package com.tejas.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejas.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

	
	
}
