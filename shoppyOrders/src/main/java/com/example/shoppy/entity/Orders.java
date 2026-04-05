package com.example.shoppy.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	@Column
	private Integer userId;
	
	@Column
	private Integer total;

	@Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
	private String status = "PENDING";

	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<OrderItems> orderItems = new ArrayList<>();
	
	public void addOrderItem(OrderItems oItem) {
		this.orderItems.add(oItem);
		oItem.setOrders(this);
	}
	
	@PrePersist
	private void onCreate() {
		
	}
	
}
