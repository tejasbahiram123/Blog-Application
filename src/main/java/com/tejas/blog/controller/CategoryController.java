package com.tejas.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.tejas.blog.payloads.CategoryDto;
import com.tejas.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	/**
	 * @author tejas
	 * @param categoryDto
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	/**
	 * @author tejas
	 * @param categoryDto
	 * @param catId
	 * @return
	 */
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId){
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, catId);
				return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
		
	}
	/**
	 * @author tejas
	 * @param catId
	 * @return
	 */
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponce>(new ApiResponce(ApiConstants.DELETE_CATEGORY,true)
				,HttpStatus.OK);
	}
	/**
	 * @author tejas
	 * @param catId
	 * @return
	 */
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
		CategoryDto category = this.categoryService.getCategory(catId);
		return new ResponseEntity<>(category,HttpStatus.OK);
			
	}
	/**
	 * @author tejas
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
	List<CategoryDto> category = this.categoryService.getAllCategories();
		
		return ResponseEntity.ok(category);	
	}
	
	
	
}
