package com.api.gatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayMicroserviceApplication.class, args);
	}
}
