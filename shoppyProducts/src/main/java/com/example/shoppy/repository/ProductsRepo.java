package com.example.shoppy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.shoppy.entity.Products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {
	
	Page<Products> findByIsActiveTrue(Pageable pg);
	
	Page<Products> findByIsActiveTrueAndCategoryId(Integer categoryId,Pageable pg);
	
//	List<Products> findByIsActiveTrue();
//	
//	List<Products> findByIsActiveTrueAndCategoryId(Integer categoryId);
	
	Optional<Products> findByIdAndIsActiveTrue(Integer id);
	
	
	boolean existsByNameIgnoreCaseAndCategoryIdAndIsActiveTrue(String name,Integer categoryId);

}
