package com.example.shoppy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.WebModal;
import com.example.shoppy.service.ProductsService;

@RestController
@RequestMapping(value = "/api")
public class ProductsController {
	public static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	ProductsService prodService;
	
	@PostMapping("/createProduct")
	public ResponseEntity<Response> createProduct(@RequestBody WebModal webmodal){
		logger.info("createProduct triggered");
		try {
			return new ResponseEntity<Response>(prodService.createProduct(webmodal),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createProduct api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));	
		}
	}
	
	
	
	@PostMapping("/getAllProducts")
	public ResponseEntity<Response> getAllProducts(@RequestBody WebModal webmodal){
		logger.info("createProduct triggered");
		try {
			return new ResponseEntity<Response>(prodService.getAllProducts(webmodal),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createProduct api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));	
		}
	}
	
	
}
