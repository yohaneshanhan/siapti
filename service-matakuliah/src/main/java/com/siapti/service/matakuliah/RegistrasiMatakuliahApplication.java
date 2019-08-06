package com.siapti.service.matakuliah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RegistrasiMatakuliahApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrasiMatakuliahApplication.class, args);
	}

}
