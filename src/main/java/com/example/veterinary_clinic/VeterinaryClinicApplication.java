package com.example.veterinary_clinic;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class VeterinaryClinicApplication {


	public static void main(String[] args) {
		SpringApplication.run(VeterinaryClinicApplication.class, args);
	}
}
