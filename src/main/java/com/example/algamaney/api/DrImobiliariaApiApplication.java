package com.example.algamaney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.algamaney.api.config.property.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class DrImobiliariaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrImobiliariaApiApplication.class, args);
	}

}
