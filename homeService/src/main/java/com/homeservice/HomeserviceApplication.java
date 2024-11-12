package com.homeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
 // This will enable Eureka client registration
@EnableDiscoveryClient
public class HomeserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeserviceApplication.class, args);
    }
}
