package com.tejas.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tejas.blog.payloads.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String msg=ex.getMessage();
		ApiResponce apiResponce= new ApiResponce(msg,false);
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgsNotValidExceptionHandler(MethodArgumentNotValidException ex){
		
		Map<String,String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname= ((FieldError)error).getField();
			String Message = error.getDefaultMessage();
			resp.put(fieldname, Message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponce> handleApiException(ApiException ex){
		String msg=ex.getMessage();
		ApiResponce apiResponce= new ApiResponce(msg,true);
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.BAD_REQUEST);
}
}