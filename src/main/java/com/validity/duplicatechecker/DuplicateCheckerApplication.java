package com.validity.duplicatechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.validity")
public class DuplicateCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DuplicateCheckerApplication.class, args);
	}

}
