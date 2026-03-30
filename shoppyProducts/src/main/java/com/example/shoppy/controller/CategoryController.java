package com.example.shoppy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.WebModal;
import com.example.shoppy.service.CategoryService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping(value = "/api")
public class CategoryController {
	public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService catService;
	
	@PostMapping("/createCategory")
	public ResponseEntity<Response> createCategory(@RequestBody WebModal webmodal){
		logger.info("createCategory triggers");
		try {
			return new ResponseEntity<Response>(catService.createCategory(webmodal.getName(), webmodal.getParentId()),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createCategory api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,"createCategory api fail",null));
		}
	}
	
	
	@PostMapping("/updateCategory")
	public ResponseEntity<Response> updateCategory(@RequestBody WebModal webmodal){
		logger.info("updateCategory triggers");
		try {
			return new ResponseEntity<Response>(catService.updateCategory(webmodal.getId(),webmodal.getName(), webmodal.getParentId()),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateCategory api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,"updateCategory api fail",null));
		}
	}
	
	
	@PostMapping("/getAllActiveCategory")
	public ResponseEntity<Response> getAllActiveCategory(@RequestBody WebModal webmodal){
		logger.info("getAllActiveCategory triggers");
		try {
			return new ResponseEntity<Response>(catService.getAllActiveCategory(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllActiveCategory api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,"getAllActiveCategory api fail",null));
		}
	}
	
	
	@PostMapping("/getCategoryById")
	public ResponseEntity<Response> getCategoryById(@RequestBody WebModal webmodal){
		logger.info("getCategoryById triggers");
		try {
			return new ResponseEntity<Response>(catService.getCategoryById(webmodal.getId()),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createCategory api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,"createCategory api fail",null));
		}
	}
	
	
	@PostMapping("/getSubCategories")
	public ResponseEntity<Response> getSubCategories(@RequestBody WebModal webmodal){
		logger.info("getSubCategories triggers");
		try {
			return new ResponseEntity<Response>(catService.getSubCategories(webmodal.getParentId()),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoryById api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,"getCategoryById api fail",null));
		}
	}
	
	
	@PostMapping("/deactivateCategory")
	public ResponseEntity<Response> deactivateCategory(@RequestBody WebModal webmodal){
		logger.info("deactivateCategory triggers");
		try {
			return new ResponseEntity<Response>(catService.deactivateCategory(webmodal.getId()),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deactivateCategory api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,"deactivateCategory api fail",null));
		}
	}
		

}
