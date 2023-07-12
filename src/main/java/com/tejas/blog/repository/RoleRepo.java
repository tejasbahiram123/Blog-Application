package com.tejas.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejas.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

	
}
