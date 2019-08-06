package com.siapti.service.resetpass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceResetPasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceResetPasswordApplication.class, args);
	}

}
