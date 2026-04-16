package com.example.shoppy.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.shoppy.dto.OrderCreateDTO;
import com.example.shoppy.dto.Response;

//@FeignClient(name = "userService",url ="${service.user.url}")
@FeignClient(name = "SHOPPYUSERS")
public interface UsersClient {

	@PostMapping("auth/getUserById")
	Response getUserById(@RequestBody OrderCreateDTO id, @RequestHeader("Authorization") String token);

}
