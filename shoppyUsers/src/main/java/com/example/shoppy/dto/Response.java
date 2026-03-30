package com.example.shoppy.dto;

import java.util.List;

public class Response {

	private Integer status;
	private String message;
	private Object data;
	
	private List<ErrorDetails> errors;

	public Response(Integer status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public Response(Integer status, String message, List<ErrorDetails> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<ErrorDetails> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetails> errors) {
		this.errors = errors;
	}
	
	

}