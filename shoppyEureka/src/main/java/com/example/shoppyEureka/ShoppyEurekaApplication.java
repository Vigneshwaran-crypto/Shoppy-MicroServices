package com.example.shoppyEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ShoppyEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppyEurekaApplication.class, args);
	}

}
