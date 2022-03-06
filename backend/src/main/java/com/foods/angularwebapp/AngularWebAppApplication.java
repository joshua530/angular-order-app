package com.foods.angularwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class AngularWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularWebAppApplication.class, args);
	}
}
