package com.example.shoppy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppy.entity.Orders;


@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {
	
	

}
