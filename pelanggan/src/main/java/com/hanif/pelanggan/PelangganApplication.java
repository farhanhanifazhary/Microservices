package com.hanif.pelanggan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class PelangganApplication {

	public static void main(String[] args) {
		SpringApplication.run(PelangganApplication.class, args);
	}

	@GetMapping("/")
	public String getMethodName() {
		return "Hewo";
	}

}
