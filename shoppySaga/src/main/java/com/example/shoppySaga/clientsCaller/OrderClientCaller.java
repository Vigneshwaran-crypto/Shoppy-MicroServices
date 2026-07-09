package com.example.shoppySaga.clientsCaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppySaga.clients.OrderClient;
import com.example.shoppySaga.clients.ProductClient;
import com.example.shoppySaga.dto.OrderCreateDTO;
import com.example.shoppySaga.dto.Response;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;




@Service
public class OrderClientCaller {
	
	private static final Logger log = LoggerFactory.getLogger(OrderClientCaller.class);
	
	@Autowired
	private OrderClient ordClient;
	
	
	@Autowired
	private ProductClient prodClient;
	
	@CircuitBreaker(name = "saga",fallbackMethod = "fallBackHandler")
	public Response createOrder(OrderCreateDTO order,String token) {
		return ordClient.createOrder(order, token);
	}
	
	
	@CircuitBreaker(name = "saga",fallbackMethod = "fallBackHandler")
	public Response upateProduct(OrderCreateDTO order,String token) {
		return prodClient.updateProductQty(order, token);
	}
	
	
	 Response fallBackHandler(OrderCreateDTO order,String token,Exception ex) {
		log.info("fallBackHandler : {}",ex);
			return new Response(2,"Service Down",null);	
	}



}
