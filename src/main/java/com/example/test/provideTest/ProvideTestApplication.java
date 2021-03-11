package com.example.test.provideTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProvideTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvideTestApplication.class, args);
	}

}
