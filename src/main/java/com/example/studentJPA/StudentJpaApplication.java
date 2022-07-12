package com.example.studentJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class StudentJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentJpaApplication.class, args);
	}

}
