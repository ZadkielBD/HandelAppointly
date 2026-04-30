package com.handel.HandelAppointly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HandelAppointlyApplication {
	public static void main(String[] args) {
		SpringApplication.run(HandelAppointlyApplication.class, args);
	}
}
