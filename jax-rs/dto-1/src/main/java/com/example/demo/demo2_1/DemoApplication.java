package com.example.demo.demo2_1;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
	private final CustomerRepository customerRepository;

	// Constructor injection
	public DemoApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner myMethod() {
		return args -> {
			List<Customer> customers = customerRepository.findAll();

			for (Customer c : customers) System.out.println(c.getName());
		};
	}
}
