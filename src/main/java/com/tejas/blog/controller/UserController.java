package com.tejas.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.blog.constant.ApiConstants;
import com.tejas.blog.payloads.ApiResponce;
import com.tejas.blog.payloads.UserDto;
import com.tejas.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {


	@Autowired
	private UserService userService;
	
	//POST-CREATE USER
	/**
	 * @author tejas
	 * @param userDto
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto user = this.userService.CreateUser(userDto);
		
		return new ResponseEntity<>(user,HttpStatus.CREATED);		
	}
	
	
	//PUT -UPDATE USER
	/**
	 * @author tejas
	 * @param userDto
	 * @param userId
	 * @return
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
	UserDto updateUser = this.userService.updateUser(userDto, userId);
		
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
		
	}
	
	//DELETE
	/**
	 * @author tejas
	 * @param userId
	 * Access by ADMIN only
	 * @return
	 */
//	@PreAuthorize("hasRole('ADMIN_USER')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
	
		//	return new ResponseEntity<>(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<ApiResponce>(new ApiResponce(ApiConstants.DELETE_USER,true),HttpStatus.OK);
	}
	//GET
	/**
	 * @author tejas
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
	return ResponseEntity.ok(this.userService.getAllUsers());
		
	}
	//GET
	/**
	 * @author tejas
	 * @param userId
	 * @return
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId){
		
	return ResponseEntity.ok(this.userService.getUserById(userId));
		
	}
	
}
