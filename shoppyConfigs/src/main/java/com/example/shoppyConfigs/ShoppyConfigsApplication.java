package com.example.shoppyConfigs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ShoppyConfigsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppyConfigsApplication.class, args);
	}

}
