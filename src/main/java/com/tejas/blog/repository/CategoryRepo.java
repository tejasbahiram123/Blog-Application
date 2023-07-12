package com.tejas.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejas.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	

}
