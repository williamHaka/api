package com.hakalab.api;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ApiApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(ApiApplication.class, args);
	}
}
