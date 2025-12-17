package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.entity.User;
import com.example.service.UserService;

@SpringBootApplication
public class ValidatorAndInterceptorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidatorAndInterceptorApplication.class, args);
	}
	
	
	@Bean
    CommandLineRunner init(UserService userService) {
        return args -> {

            if (userService.login("admin", "123") == null) {
                userService.save(new User(null, "admin", "123", "ADMIN"));
            }

            if (userService.login("user", "123") == null) {
                userService.save(new User(null, "user", "123", "USER"));
            }
        };
    }
}
