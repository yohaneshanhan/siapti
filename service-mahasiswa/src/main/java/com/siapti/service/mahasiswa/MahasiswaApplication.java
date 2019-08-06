package com.siapti.service.mahasiswa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MahasiswaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MahasiswaApplication.class, args);
	}

}

