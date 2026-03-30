package com.example.shoppy.service;

import com.example.shoppy.dto.Response;

public interface CategoryService {
	
	Response createCategory(String name , Integer parentId);
	
	Response updateCategory(Integer id,String name , Integer parentId);
	
	Response getAllActiveCategory();
	
	Response getCategoryById(Integer id);
	
	Response getSubCategories(Integer parentId);
	
	Response deactivateCategory(Integer id);
	
}
