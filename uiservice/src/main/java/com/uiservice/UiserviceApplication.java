package com.uiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class UiserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiserviceApplication.class, args);
	}

}
