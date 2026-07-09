package com.example.shoppySaga.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemsDTO {
	
	private Integer id;
	
	private Integer orderId;
	

	private Integer productId;

	private Integer qty;
	
	private Integer priceAtThatTime;
	


}
