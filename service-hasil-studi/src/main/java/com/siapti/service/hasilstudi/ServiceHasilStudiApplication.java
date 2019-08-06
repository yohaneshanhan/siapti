package com.siapti.service.hasilstudi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceHasilStudiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHasilStudiApplication.class, args);
	}

}
