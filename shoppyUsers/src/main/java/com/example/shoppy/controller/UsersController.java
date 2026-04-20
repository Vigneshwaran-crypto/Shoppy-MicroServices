package com.example.shoppy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppy.dto.CreateUserDTO;
import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.SignInDTO;
import com.example.shoppy.repository.UsersRepo;
import com.example.shoppy.security.JwtUtils;
import com.example.shoppy.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class UsersController {
	public static final Logger log = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	UsersService usrService;
	
	@Autowired
	private UsersRepo usrRepo;
	
	@Autowired
	private JwtUtils jwtUtils;
		
	@PostMapping("/createUser")
	public ResponseEntity<Response> createUser(@Valid @RequestBody CreateUserDTO req,HttpServletRequest hd){
		log.info("createUser api hit");
		try {
			return new ResponseEntity<Response>(usrService.createUser(req,hd),HttpStatus.OK);
		} catch (Exception e) {
			
			log.info("createUser api catch : {} ",e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
	}
	
//	@PreAuthorize("hasRole('Admin','User')")
	@PostMapping("/signIn")
	public ResponseEntity<Response> signIn(@Valid @RequestBody SignInDTO req,HttpServletRequest hd){
		log.info("signIn api hit");
		try {
			return new ResponseEntity<Response>(usrService.signIn(req,hd),HttpStatus.OK);
		} catch (Exception e) {
			log.info("signIn api catch : {}",e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
	}
	
	
	
	@PostMapping("/getUserDetails")
	public ResponseEntity<Response> getUserDetails(@RequestBody SignInDTO req,HttpServletRequest hd){
		log.info("getUserDetails api hit : {}",SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

		log.info("extracted user from token : {}",jwtUtils.extractAllClaims(hd.getHeader("Authorization").substring(7)));

		try {
			return new ResponseEntity<Response>(new Response(0,"Success",usrRepo.findByEmail(req.getEmail())),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("signIn api catch : {}",e.getMessage());
			
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
	}

	
	@PostMapping("/getUserById")
	public ResponseEntity<Response> getUserById(@RequestBody CreateUserDTO id,HttpServletRequest hd){
		log.info("getUserDetails api hit : {}",SecurityContextHolder.getContext().getAuthentication());
		try {
			return new ResponseEntity<Response>(usrService.getUserById(id,hd),HttpStatus.OK);
		} catch (Exception e) {
			log.info("getUserById api catch : {}",e);
			return ResponseEntity.internalServerError().body(new Response(0,e.getMessage(),null));
		}
		
	}
	
	
}
