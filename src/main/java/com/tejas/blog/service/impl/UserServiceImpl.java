package com.tejas.blog.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tejas.blog.exceptions.*;
import com.tejas.blog.constant.AppConstants;
import com.tejas.blog.entities.Role;
import com.tejas.blog.entities.User;
import com.tejas.blog.payloads.UserDto;
import com.tejas.blog.repository.RoleRepo;
import com.tejas.blog.repository.UserRepo;
import com.tejas.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserDto CreateUser(UserDto userDto) {
		logger.info("User method for create user starting");
		User user =this.dtoToUser(userDto);
        User save = this.userRepo.save(user);
        logger.info("User method for create user is end. ");
		return this.userToDto(save);
	}

	

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		logger.info("User method for update user starting");
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
	
		User updatedUser = this.userRepo.save(user);
		UserDto userToDto = this.userToDto(updatedUser);
		logger.info("User method for update user end.");
		
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		logger.info("User method for get userbyId starting");
		User user= this.userRepo.findById(userId)
				.orElseThrow(() ->new ResourceNotFoundException("User", "Id", userId));
		logger.info("User method for get userbyId end");
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		logger.info("User method for getAll user starting");
		List<User> users = this.userRepo.findAll();
		List<UserDto> collectusers = users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		logger.info("User method for getAll user end.");
		return collectusers;
	}

	@Override
	public void deleteUser(Integer userId) {
		logger.info("User method for delete user starting");
	User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));	
	logger.info("User method for delete user end");
	this.userRepo.delete(user);
	
	}
   
	
	private User dtoToUser(UserDto userDto) {
		logger.info("User method for convert dto to user starting");
	User user = this.modelMapper.map(userDto, User.class);
	
//	user.setId(userDto.getId());
//	user.setUsername(userDto.getUsername());          //mannually
//	user.setEmail(userDto.getEmail());
//	user.setPassword(userDto.getPassword());
//	user.setAbout(userDto.getAbout());
//		
	logger.info("User method for convert dto to user end");
	return user;
	
}
     public UserDto userToDto(User user) {
    	 logger.info("User method for convert user to Dto starting");
    	 UserDto userDto=this.modelMapper.map(user, UserDto.class);
    	 
//    	 userDto.setId(user.getId());
//    	 userDto.setUsername(user.getUsername());
//    	 userDto.setEmail(user.getEmail());
//    	 userDto.setPassword(user.getPassword());
//    	 userDto.setAbout(user.getAbout());
//    	
    	 logger.info("User method for  user to dto end");
    	 return userDto;
     }



	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles
		  Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		  
		  user.getRoles().add(role);
		  
		  User newUser = this.userRepo.save(user);
		
		return this.modelMapper.map(newUser, UserDto.class);
	}

	
}
