package com.example.shoppy.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrdersDTO {

	private Integer orderId;

	private Integer userId;

	private Integer total;

	private String status = "PENDING";

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private List<OrderItemsDTO> orderItems = new ArrayList<>();

	public void addOrderItem(OrderItemsDTO oItem) {
		this.orderItems.add(oItem);
		oItem.setOrders(this);
	}

}
