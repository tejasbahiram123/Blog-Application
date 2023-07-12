package com.tejas.blog.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {

	
	private List<PostDto> content;
	private int pageNumber;
	private int pagesize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
	
}
