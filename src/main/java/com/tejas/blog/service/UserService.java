package com.tejas.blog.service;

import java.util.List;

import com.tejas.blog.entities.User;
import com.tejas.blog.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto user);
	
	UserDto CreateUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
}
