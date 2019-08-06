package com.siapti.service.walistudi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class WaliStudiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaliStudiApplication.class, args);
	}

}

