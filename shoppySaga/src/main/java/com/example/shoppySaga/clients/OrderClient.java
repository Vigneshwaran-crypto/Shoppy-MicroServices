package com.example.shoppySaga.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.shoppySaga.dto.OrderCreateDTO;
import com.example.shoppySaga.dto.Response;


@FeignClient(name = "SHOPPYORDERS")
public interface OrderClient {
	
	@PostMapping("order/createOrder")
	Response createOrder(@RequestBody OrderCreateDTO id, @RequestHeader("Authorization") String token);
	
	@PostMapping("order/updateOrderById")
	Response updateOrderById(@RequestBody OrderCreateDTO id, @RequestHeader("Authorization") String token);
	
	@PostMapping("order/getOrderById")
	Response getOrderById(@RequestBody OrderCreateDTO id, @RequestHeader("Authorization") String token);

}
