package com.example.shoppy.service;

import com.example.shoppy.dto.OrderCreateDTO;
import com.example.shoppy.dto.Response;

import jakarta.servlet.http.HttpServletRequest;

public interface OrdersService {
	
	 Response createOrder( OrderCreateDTO order,HttpServletRequest req); 
	 
	 Response getOrderById( OrderCreateDTO order,HttpServletRequest req); 
	 
	 
}
