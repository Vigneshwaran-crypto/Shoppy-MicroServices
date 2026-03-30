package com.example.shoppy.service;

import java.math.BigDecimal;

import com.example.shoppy.dto.Response;
import com.example.shoppy.dto.WebModal;

public interface ProductsService {
	
	Response createProduct(WebModal webmodal);
	
	Response getAllProducts(WebModal webmodal);

}
