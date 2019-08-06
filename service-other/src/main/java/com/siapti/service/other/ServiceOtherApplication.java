package com.siapti.service.other;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceOtherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOtherApplication.class, args);
	}

}
