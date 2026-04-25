package com.example.shoppy.serviceCaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppy.clients.UsersClient;
import com.example.shoppy.dto.OrderCreateDTO;
import com.example.shoppy.dto.Response;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserServiceCaller {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceCaller.class);
	
	@Autowired
	private UsersClient userClient;
	
	@CircuitBreaker(name = "shoppyUsers",fallbackMethod = "getUserByIdFallBack" )
	public Response getUserById(OrderCreateDTO order,String token) {
		return userClient.getUserById(order, token);
	}
	
	 Response getUserByIdFallBack(OrderCreateDTO order,String token,Exception ex) {
		log.info("getUserByIdFallBack : {}",ex);
		return new Response(2,"User Service Down",null);	
	}

}
