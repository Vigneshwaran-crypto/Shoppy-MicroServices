package com.example.shoppy.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateDTO {

	private Integer orderId;

	@NotNull(message = "userId Required")
	private Integer userId;
	
	private Integer total;

	private String status;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	@NotNull(message = "At least one Order Item is required")
	@Size(min=1,message = "order must contain one item")
	private List<OrderItemsDTO> orderItems;
	
}
