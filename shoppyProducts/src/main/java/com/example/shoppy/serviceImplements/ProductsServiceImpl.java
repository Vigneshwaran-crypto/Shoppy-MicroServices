package com.example.shoppy.serviceImplements;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.WebModal;
import com.example.shoppy.entity.Inventory;
import com.example.shoppy.entity.Products;
import com.example.shoppy.repository.CategoryRepo;
import com.example.shoppy.repository.InventoryRepo;
import com.example.shoppy.repository.ProductsRepo;
import com.example.shoppy.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

	public static final Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);

	@Autowired
	private ProductsRepo prodRepo;

	@Autowired
	private CategoryRepo catRepo;

	@Autowired
	private InventoryRepo inventRepo;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ )
	public Response createProduct(WebModal webmodal) {
	try {

		String name = webmodal.getName() == null ? "" : webmodal.getName().trim();

		if (name.equalsIgnoreCase(""))
			return new Response(0, "Name is missing", null);

		if (webmodal.getCategoryId() == null || !catRepo.existsByIdAndIsActiveTrue(webmodal.getCategoryId()))
			return new Response(0, "Invalid categoryId", null);

		if (webmodal.getPrice() == null || webmodal.getPrice().signum() < 0)
			return new Response(0, "Invalid price", null);

		if (prodRepo.existsByNameIgnoreCaseAndCategoryIdAndIsActiveTrue(name, webmodal.getCategoryId()))
			return new Response(0, "Unique name required", null);

		Products pr = new Products();
		pr.setName(name);
		pr.setDescription(webmodal.getDescription());
		pr.setPrice(webmodal.getPrice());
		pr.setCategoryId(webmodal.getCategoryId());
		Products prod = prodRepo.save(pr);

		Inventory stk = new Inventory();
		stk.setProductId(prod.getId());
		inventRepo.save(stk);

		return new Response(1, "success", prod);
		
//		throw new RuntimeException("Test Errooccured "); // manually print exception
	} catch (Exception e) {
//		e.printStackTrace(); // can't print stack while transaction
		throw new RuntimeException(e); // manually print exception
		
//		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	
	}
//	
//	return new Response(0,"createProduct method fails",null);
	}

	@Override
	public Response getAllProducts(WebModal webmodal) {
		try {

			if (webmodal.getSortBy() == null || webmodal.getSortBy().trim().equalsIgnoreCase(""))
				return new Response(0, "Sorted By is missing", null);

			Integer pgNo = webmodal.getPageNo() < 0 ? 0 : webmodal.getPageNo();
			Integer pgSize = webmodal.getPageSize() < 10 ? 10 : webmodal.getPageSize();
			Sort srt = webmodal.getIsAsc() ? Sort.by(webmodal.getSortBy()).ascending()
					: Sort.by(webmodal.getSortBy()).descending();

			Pageable page = PageRequest.of(pgNo, pgSize, srt);
			Page<Products> prodList;
			
			if (webmodal.getCategoryId() != null && webmodal.getCategoryId() > -1) {
				prodList = prodRepo.findByIsActiveTrueAndCategoryId(webmodal.getCategoryId(), page);
			} else {
				prodList = prodRepo.findByIsActiveTrue(page);
			}
			
		
			


			return new Response(1, "success", prodList);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(0, "getAllProducts method fails", null);
		}
	}

	@Override
	public Response getFullProducts() {
		try {
			String redisKey = "product::all";
			Object cache = redisTemplate.opsForValue().get(redisKey);
			
			if(cache != null) {
				return new Response(0, "success", (List<Products>) cache);
			}

			
			List<Products> prods = prodRepo.findAll();
			redisTemplate.opsForValue().set(redisKey, prods);
			
//			redisTemplate.opsForValue().set(redisKey, prods,5,TimeUnit.MINUTES); // TTL - time to live - now the data only live in this 5 minutes
			
//			redisTemplate.delete(redisKey) //delete in where you update the values fo db , so the cache won't have tp use the stale data
			
			return new Response(0, "success", prods);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(0, "getAllProducts method fails", null);
		}
	}

}
