package com.example.shoppy.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemsDTO {
	
	private Integer id;
	
	private Integer orderId;
	
	@NotNull(message = "Product Id is Required")
	private Integer productId;

	@NotNull(message = "Quantity is Required")
	@Positive(message = "Quantity must be positive")
	private Integer qty;
	
	private Integer priceAtThatTime;
	


}
