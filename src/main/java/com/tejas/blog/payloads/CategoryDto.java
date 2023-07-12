package com.tejas.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min=4,message="min size of category tital is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=10, message="min size of category description is 10 char")
	private String categoryDescription;
}
