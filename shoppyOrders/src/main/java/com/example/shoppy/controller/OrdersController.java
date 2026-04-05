package com.example.shoppy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppy.dto.OrderCreateDTO;
import com.example.shoppy.dto.Response;
import com.example.shoppy.service.OrdersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class OrdersController {
	public static final Logger log = LoggerFactory.getLogger(OrdersController.class);
		
	@Autowired
	private OrdersService orderService;

	@PostMapping("/createOrder")
	public ResponseEntity<Response> createOrder(@Valid @RequestBody OrderCreateDTO order,HttpServletRequest req){
		
		try {
			return new ResponseEntity<Response>(orderService.createOrder(order, req),HttpStatus.OK);
		} catch (Exception e) {
			log.info("createOrder api catch : "+e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
		
	}
	
	
	
	@PostMapping("/getOrderById")
	public ResponseEntity<Response> getOrderById(@RequestBody OrderCreateDTO order,HttpServletRequest req){
		
		try {
			return new ResponseEntity<Response>(orderService.getOrderById(order, req),HttpStatus.OK);
		} catch (Exception e) {
			log.info("getOrderById api catch : "+e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
		
	}
	
}
