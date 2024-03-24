package com.example.depositService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class DepositService {

	public static void main(String[] args) {
		SpringApplication.run(DepositService.class, args);
	}

}