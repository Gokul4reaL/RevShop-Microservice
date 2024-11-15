package com.sellerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SellerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerserviceApplication.class, args);
	}

}