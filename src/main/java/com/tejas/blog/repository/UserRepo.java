package com.tejas.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejas.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	
	Optional<User> findByEmail(String email);
	
	
}
