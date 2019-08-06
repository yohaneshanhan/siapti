package com.siapti.service.idgenerator;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceIdGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceIdGeneratorApplication.class, args);
	}

}
