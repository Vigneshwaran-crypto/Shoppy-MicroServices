package com.example.shoppy.kafka;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.shoppy.dto.OrderCreateDTO;
import com.example.shoppy.entity.Orders;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objMapper;

	public void publishOrderCreateEvent(Orders event) {
		Map<String, Object> msg = objMapper.convertValue(event, new TypeReference<Map<String,Object>>() {});
		kafkaTemplate.send("order-created", msg);
	}
	
//	
//	public void publishOrderCreateEvent(Map<String, Object> event) {
//		kafkaTemplate.send("order-created", event);
//	}

}
