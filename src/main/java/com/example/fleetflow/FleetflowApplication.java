package com.example.fleetflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class FleetflowApplication {

	@RequestMapping("/")
	String home() {
		return "FleetFlow Logistics Management System";
	}
	public static void main(String[] args) {
		SpringApplication.run(FleetflowApplication.class, args);
	}

}	
	