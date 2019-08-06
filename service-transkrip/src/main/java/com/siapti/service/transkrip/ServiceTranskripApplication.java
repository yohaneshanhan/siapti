package com.siapti.service.transkrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceTranskripApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTranskripApplication.class, args);
	}

}
