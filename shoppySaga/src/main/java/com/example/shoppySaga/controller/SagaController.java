package com.example.shoppySaga.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppySaga.dto.OrderCreateDTO;
import com.example.shoppySaga.dto.Response;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/saga")
public class SagaController {
	public static final Logger log = LoggerFactory.getLogger(SagaController.class);

	
	@PostMapping("/createOrder")
	public ResponseEntity<Response> createOrder(@RequestBody OrderCreateDTO order,HttpServletRequest req){
	
		try {
			return new ResponseEntity<Response>(orderService.createOrder(order, req),HttpStatus.OK);
		} catch (Exception e) {
			log.info("createOrder api catch : "+e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
		
	}
	
	
	@PostMapping("/updateOrderById")
	public ResponseEntity<Response> updateOrderById(@RequestBody OrderCreateDTO order,HttpServletRequest req){
		
		try {
			return new ResponseEntity<Response>(orderService.updateOrderById(order, req),HttpStatus.OK);
		} catch (Exception e) {
			log.info("getOrderById api catch : "+e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
		
	}

}
