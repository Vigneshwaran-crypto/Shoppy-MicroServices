package com.example.shoppy.service;

import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.SignInDTO;
import com.example.shoppy.dto.CreateUserDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface UsersService {
	
	Response createUser(CreateUserDTO req, HttpServletRequest hd);
	
	Response signIn(SignInDTO req, HttpServletRequest hd);

}
