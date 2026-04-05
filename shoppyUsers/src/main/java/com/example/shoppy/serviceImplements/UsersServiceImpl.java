package com.example.shoppy.serviceImplements;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shoppy.dto.CreateUserDTO;
import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.SignInDTO;
import com.example.shoppy.entity.User;
import com.example.shoppy.exceptions.BusinessException;
import com.example.shoppy.repository.UsersRepo;
import com.example.shoppy.security.JwtUtils;
import com.example.shoppy.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class UsersServiceImpl implements UsersService {

	public static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired
	private UsersRepo usrRepo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private ObjectMapper objMapper;

	@Override
	public Response createUser(CreateUserDTO req, HttpServletRequest hd) {
		try {
			
			if(usrRepo.existsByEmail(req.getEmail())) {
				throw new BusinessException("Email already exists");
			}
			
			User usr = new User();
			usr.setEmail(req.getEmail());
			usr.setName(req.getName());
			usr.setPhone(req.getPhone());
			usr.setAddress(req.getAddress());
			usr.setPincode(req.getPincode());
			
			usr.setPassword(passEncoder.encode(req.getPassword()));
			
			User svdUsr = usrRepo.save(usr);
			svdUsr.setPassword(null);
			return new Response(1,"User Created Successfully",svdUsr);
		} catch (Exception e) {
			log.error("createUser method catch : {}",e);
			return new Response(0,e.getMessage(),null);
		}
	}


	@Override
	public Response signIn(SignInDTO req, HttpServletRequest hd) {
		try {
			User usrFromDB = usrRepo.findByEmail(req.getEmail());
			
			if(usrFromDB == null) throw new BusinessException("No User Found In This Mail");
			
			boolean isPassMatched = passEncoder.matches(req.getPassword(), usrFromDB.getPassword());
			
			if(!isPassMatched) throw new BusinessException("Password Missmatched");
			
			Map<String,Object> usrData = objMapper.convertValue(usrFromDB, new TypeReference<Map<String,Object>>() {});
			usrData.remove("password");
			usrData.put("token",jwtUtils.generateToken(usrFromDB.getEmail(), usrData));
			
			return new Response(1,"Sigin Successfully",usrData);
		} catch (Exception e) {
			log.error("signIn method catch : {}",e);
			return new Response(0,e.getMessage(),null);
		}
	}


	@Override
	public Response getUserById(CreateUserDTO usr, HttpServletRequest hd) {
		try {
			Optional<User> isUser = usrRepo.findByUserIdAndIsActiveTrue(usr.getUserId());
			
			if(isUser.isPresent()) {
				isUser.get().setPassword(null);
				return new Response(1,"User Data",isUser.get());
			}
			return new Response(0,"No User For This UserId",null);
		} catch (Exception e) {
			log.error("getUserById method catch : {}",e);
			return new Response(0,e.getMessage(),null);
		}
		
	}

}
