package com.tejas.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.blog.constant.ApiConstants;
import com.tejas.blog.payloads.ApiResponce;
import com.tejas.blog.payloads.CommentDto;
import com.tejas.blog.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){
		CommentDto createComment = this.commentService.createComment(comment, postId);
		
		return new ResponseEntity<CommentDto>(createComment ,HttpStatus.CREATED);		
	}
	

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponce> deleteComment( @PathVariable Integer commentId){
	this.commentService.deleteComment(commentId);
	
	return new ResponseEntity<ApiResponce>(new ApiResponce(ApiConstants.DELETE_COMMENT,true),HttpStatus.OK);
				
	}
	
	
}
