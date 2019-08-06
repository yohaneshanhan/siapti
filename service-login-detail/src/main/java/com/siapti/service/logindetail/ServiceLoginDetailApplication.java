package com.siapti.service.logindetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceLoginDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceLoginDetailApplication.class, args);
	}

}
