package com.example.shoppy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppy.entity.Orders;


@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {

	Optional<Orders> findByOrderId(Integer orderId);
	
}
