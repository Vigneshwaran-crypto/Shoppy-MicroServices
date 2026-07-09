package com.example.shoppySaga.dto;

import java.time.LocalDateTime;
import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreateDTO {

	private Integer orderId;


	private Integer userId;
	
	private Integer total;

	private String status;
	
	private Integer qty;
	
	private Integer productId;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	private List<OrderItemsDTO> orderItems;
	
}
