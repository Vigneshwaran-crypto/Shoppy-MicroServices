package com.example.shoppy.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private Integer productId;
	
	private Integer availableQty;
	
	private Integer reservedQty;
	
	@Version
	private Integer version;
	
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void onCreate() {
		updatedAt =  LocalDateTime.now();
		availableQty = 0;
		reservedQty = 10;
	}
	
	@PreUpdate
	public void onUpdate() {
		updatedAt =  LocalDateTime.now();
	}
	

}
