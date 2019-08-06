package com.siapti.servicekota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceKotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceKotaApplication.class, args);
	}

	
}