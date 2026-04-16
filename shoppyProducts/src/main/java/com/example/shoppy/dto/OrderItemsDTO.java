package com.example.shoppy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsDTO {
	
	private Integer orderItemId;
	
	private Integer productId;
	
	private Integer qty;
	
	private Integer priceAtThatTime;
	
	private OrdersDTO orders;
	
}
