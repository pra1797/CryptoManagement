package com.cms.crypto;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.cms.crypto")
public class CryptoApplication {


	public static void main(String[] args) {
		
		SpringApplication.run(CryptoApplication.class, args);
	}

}
