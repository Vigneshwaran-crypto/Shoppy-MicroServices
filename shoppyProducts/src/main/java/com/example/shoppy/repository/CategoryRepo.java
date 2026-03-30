package com.example.shoppy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppy.entity.Categories;


@Repository
public interface CategoryRepo extends JpaRepository<Categories, Integer> {
	
	List<Categories> findByIsActiveTrue();
	
	Optional<Categories> findByIdAndIsActiveTrue(Integer id);
	
	boolean existsByNameIgnoreCase(String name);
	
	boolean existsByIdAndIsActiveTrue(Integer id);
	
	List<Categories> findByParentIdAndIsActiveTrue(Integer id);
	

}
