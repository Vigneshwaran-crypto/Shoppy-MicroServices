package com.example.shoppy.controller;

import org.hibernate.annotations.Cache;
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
import com.example.shoppy.repository.ProductsRepo;
import com.example.shoppy.service.CategoryService;
import com.example.shoppy.service.ProductsService;

@RestController
@RequestMapping(value = "/prod")
public class ProductsController {
	public static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	private ProductsService prodService;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ProductsRepo productRepo;
	
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
	
	@GetMapping("/getFullProducts")
	public ResponseEntity<Response> getFullProducts(){
		logger.info("getFullProducts triggered");
		try {
			return new ResponseEntity<Response>(prodService.getFullProducts(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createProduct api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));	
		}
	}
	
//	 productRepo.updateProductQty(item.getProductId(), item.getQty());
	
	
	@PostMapping("/updateProductQty")
	public ResponseEntity<Response> updateProductQty(@RequestBody WebModal webmodal){
		logger.info("updateProductQty triggered");
		try {
			
			productRepo.updateProductQty(webmodal.getProductId(), webmodal.getQty());

			return new ResponseEntity<Response>(new Response(1, "Product QTY updated", null),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createProduct api catch :",e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));	
		}
	}
	
}
