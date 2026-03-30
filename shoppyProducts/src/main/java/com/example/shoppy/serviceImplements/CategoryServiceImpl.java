package com.example.shoppy.serviceImplements;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppy.dto.Response;
import com.example.shoppy.entity.Categories;
import com.example.shoppy.repository.CategoryRepo;
import com.example.shoppy.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	public static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	CategoryRepo catRepo;
	

	@Override
	public Response createCategory(String name, Integer parentId) {
		try {
						
			if(catRepo.existsByNameIgnoreCase(name.trim())) {
				return new Response(0,"name already exists",null);
			}
			
			Categories cats = new Categories();
			
			if(parentId != null) {
				if(catRepo.existsByIdAndIsActiveTrue(parentId)) {
					cats.setParentId(parentId);
				}else {
					return new Response(0,"Unknown parent id",null);
				}
			}
			
			cats.setName(name.trim());
			Categories svd = catRepo.save(cats);

			return new Response(1,"success",svd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createCategory catch :",e);
			return new Response(0,"createCategory fail",null);
		}
	}


	@Override
	public Response updateCategory(Integer id, String name, Integer parentId) {
		try {
			
			Optional<Categories> cats = catRepo.findByIdAndIsActiveTrue(id);
			
			if(cats.isEmpty()) {
				return new Response(0,"No Matched Category",null);
			}
			
			Categories catByDb = cats.get();
			catByDb.setName(name);
			catByDb.setParentId(parentId);
			Categories svd = catRepo.save(catByDb);
			
			
			return new Response(1,"success",svd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateCategory catch :",e);
			return new Response(0,"updateCategory fail",null);
		}
	}


	@Override
	public Response getAllActiveCategory() {
	try {
		
		List<Categories> cats = catRepo.findByIsActiveTrue();
		return new Response(1,"success",cats);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllActiveCategory catch :",e);
			return new Response(0,"getAllActiveCategory fail",null);
		}
	}


	@Override
	public Response getCategoryById(Integer id) {
		try {
			Optional<Categories> catByDb = catRepo.findByIdAndIsActiveTrue(id);
			if(catByDb.isEmpty()) {
				return new Response(1,"No Matches",null);
			}
			return new Response(1,"success",catByDb.get());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getCategoryById catch :",e);
				return new Response(0,"getCategoryById fail",null);
			}
	}


	@Override
	public Response getSubCategories(Integer parentId) {
		try {
			List<Categories> cats = catRepo.findByParentIdAndIsActiveTrue(parentId);
			return new Response(1,"success",cats);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getSubCategories catch :",e);
				return new Response(0,"getSubCategories fail",null);
			}
	}


	@Override
	public Response deactivateCategory(Integer id) {
		try {
			
			Optional<Categories> cats = catRepo.findByIdAndIsActiveTrue(id);
			
			if(cats.isEmpty()) {
				return new Response(1,"No Matches",null);
			}
			
			Categories catByDb = cats.get();
			catByDb.setIsActive(false);
			Categories svd = catRepo.save(catByDb);
			
			return new Response(1,"test",svd);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("deactivateCategory catch :",e);
				return new Response(0,"deactivateCategory fail",null);
			}
	}

}
