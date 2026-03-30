package com.example.shoppy.service;

import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.WebModal;

import jakarta.servlet.http.HttpServletRequest;

public interface UsersService {
	
	Response createUser(WebModal req, HttpServletRequest hd);

}
