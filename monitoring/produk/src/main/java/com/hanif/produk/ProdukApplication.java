package com.hanif.produk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ProdukApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdukApplication.class, args);
	}

	@GetMapping("/")
	public String getMethodName() {
		return "Hewo";
	}
	

}
