package com.example.shoppy.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
	
	private Integer userId;
	
	@NotBlank(message =  "Email is Required")
	@Email
	private String email;
	
	@NotBlank(message =  "Password is Required")
	private String password;
	
	@NotBlank(message =  "Name is Required")
	private String name;
	
	@NotBlank(message =  "Phone is Required")
	private String phone;
	
	private String role;
	
	@NotBlank(message =  "Address is Required")
	private String address;
	
	@NotBlank(message =  "Pincode is Required")
	private String pincode;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

}
