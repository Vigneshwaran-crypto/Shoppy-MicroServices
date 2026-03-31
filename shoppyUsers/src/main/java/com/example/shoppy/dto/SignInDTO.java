package com.example.shoppy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignInDTO {

	@NotBlank(message = "Email is required")
	@Email
	private String email; 
	
	@NotBlank(message = "Password is required")
	private String password; 

}
