package com.tq.mindset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MindsetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindsetApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			// Your command logic here
			System.out.println("Mindset Application has started!");
			// You can execute any other logic or call services here
		};
	}
}
