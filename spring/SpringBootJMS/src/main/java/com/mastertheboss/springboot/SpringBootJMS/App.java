package com.mastertheboss.springboot.SpringBootJMS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class App {
	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Value("${myqueue}")
	String queue;

	@Bean
	CommandLineRunner start(JmsTemplate template) {
		return args -> {
			log.info("Sending> ...");

			template.convertAndSend(queue, "Hello World from Spring Boot!");
		};
	}
}
