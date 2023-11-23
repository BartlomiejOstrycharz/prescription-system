package com.prescription.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BackendPrecriptionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPrecriptionApiApplication.class, args);
	}

	@GetMapping("/welcome") //http://localhost:8080/welcome
	public String welcome(){
		return "welcome";
	}

}
