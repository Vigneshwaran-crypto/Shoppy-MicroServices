package com.example.shoppy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ShoppyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppyApplication.class, args);
	}

}
