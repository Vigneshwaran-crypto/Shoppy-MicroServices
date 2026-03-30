package com.example.shoppy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.shoppy.entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

}
