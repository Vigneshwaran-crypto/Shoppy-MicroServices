package com.example.shoppy.serviceImplements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.WebModal;
import com.example.shoppy.entity.Users;
import com.example.shoppy.exceptions.BusinessException;
import com.example.shoppy.repository.UsersRepo;
import com.example.shoppy.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsersServiceImpl implements UsersService {

	public static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired
	UsersRepo usrRepo;

	@Override
	public Response createUser(WebModal req, HttpServletRequest hd) {
		try {
			
			if(usrRepo.existsByEmail(req.getEmail())) {
				throw new BusinessException("Email already exists");
			}
			
			Users usr = new Users();
			usr.setEmail(req.getEmail());
			usr.setName(req.getName());
			usr.setPhone(req.getPhone());
			usr.setAddress(req.getAddress());
			usr.setPincode(req.getPincode());
			
			Users svdUsr = usrRepo.save(usr);
			return new Response(1,"User Created Successfully",svdUsr);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("createUser method catch : {}",e);
			return new Response(0,e.getMessage(),null);
		}
	}

	

}
