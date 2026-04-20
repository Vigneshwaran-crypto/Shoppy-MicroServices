package com.example.shoppy.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import tools.jackson.databind.ObjectMapper;

@Configuration
public class KafkaConsumerConfig {
	
	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public ConsumerFactory<String, Object> consumerFactory(){
		
		JacksonJsonDeserializer<Object> deserializer =
		        new JacksonJsonDeserializer<>(Object.class,false);

		Map<String, Object> config = new HashMap<>();
		
		config.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
		config.put(JacksonJsonDeserializer.USE_TYPE_INFO_HEADERS, false);		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,  "localhost:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "shoppy-events");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,  StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,  JacksonJsonDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(config,
	            new StringDeserializer(),
	            deserializer);
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = 
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;	
	}

}
