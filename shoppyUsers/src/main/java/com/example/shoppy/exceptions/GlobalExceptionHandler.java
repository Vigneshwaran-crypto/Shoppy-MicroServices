package com.example.shoppy.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.shoppy.dto.ErrorDetails;
import com.example.shoppy.dto.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response validationErrorhandler(MethodArgumentNotValidException ex) {
		List<ErrorDetails> errors = new ArrayList<>();
		ex.getBindingResult().getFieldErrors().forEach(err -> {
			errors.add(new ErrorDetails(err.getField(), err.getDefaultMessage()));
		});
		return new Response(0, "Validation Error", errors);
	}
	
	
	@ExceptionHandler(BusinessException.class)
	public Response BusinessExpHandler(BusinessException ex) {
		return new Response(0,ex.getMessage(),null);
	}

}
