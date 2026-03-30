package com.example.shoppy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.WebModal;
import com.example.shoppy.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class UsersController {
	public static final Logger log = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	UsersService usrService;
		
	@PostMapping("/createUser")
	public ResponseEntity<Response> createUser(@Valid @RequestBody WebModal req,HttpServletRequest hd){
		log.info("createUser api hit");
		try {
			return new ResponseEntity<Response>(usrService.createUser(req,hd),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("createUser api catch : {} ",e.getMessage());
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
	}
	

	
	
	
}
