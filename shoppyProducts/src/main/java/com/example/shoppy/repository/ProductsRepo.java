package com.example.shoppy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.shoppy.entity.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {
	
	Page<Products> findByIsActiveTrue(Pageable pg);
	
	Page<Products> findByIsActiveTrueAndCategoryId(Integer categoryId,Pageable pg);
	
	Optional<Products> findByIdAndIsActiveTrue(Integer id);
	
	boolean existsByNameIgnoreCaseAndCategoryIdAndIsActiveTrue(String name,Integer categoryId);
	
	@Modifying
	@Transactional
	@Query("UPDATE Products p SET p.qty = p.qty - :qty WHERE p.id = :productId")
	Integer updateProductQty(Integer productId,Integer qty);

}
