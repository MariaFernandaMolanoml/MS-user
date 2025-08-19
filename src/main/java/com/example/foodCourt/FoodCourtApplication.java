package com.example.foodCourt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FoodCourtApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCourtApplication.class, args);
	}

}
