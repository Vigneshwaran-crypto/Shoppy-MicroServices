package com.example.shoppySaga.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.shoppySaga.dto.OrderCreateDTO;
import com.example.shoppySaga.dto.Response;


@FeignClient(name = "SHOPPYPRODUCTS")
public interface ProductClient {
	
	@PostMapping("prod/updateProductQty")
	Response updateProductQty(@RequestBody OrderCreateDTO id, @RequestHeader("Authorization") String token);

}
