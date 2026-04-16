package com.example.shoppy.kafka;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.shoppy.dto.OrderCreateDTO;
import com.example.shoppy.dto.OrdersDTO;
import com.example.shoppy.repository.ProductsRepo;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {

	public static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

	@Autowired
	private ObjectMapper objMapper;

	@Autowired
	private ProductsRepo productRepo;

	@KafkaListener(topics = "order-created", groupId = "shoppy-events")
	public void orderCreatedConsumer(Map<String, Object> event) {

		OrdersDTO orders = objMapper.convertValue(event, new TypeReference<OrdersDTO>() {
		});
		log.info("orderCreatedConsumer in products : {}", event);
		orders.getOrderItems().forEach(item -> {
			try {
				Integer updates = productRepo.updateProductQty(item.getProductId(), item.getQty());
				log.info("updated row count : {} for productId : {}", updates, item.getProductId());
			} catch (Exception e) {
				log.info("Reducing Order Items QTY catch : {}", e);
			}

		});

	}

}
