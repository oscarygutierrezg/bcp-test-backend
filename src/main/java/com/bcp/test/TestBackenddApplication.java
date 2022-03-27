package com.bcp.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bcp.test")
public class TestBackenddApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBackenddApplication.class, args);
	}
	
	
}