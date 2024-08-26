package com.example.demo.demo2_1;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.repository.CriteriaRepository;
import com.example.demo.demo2_1.repository.CustomerRepository;
import com.example.demo.demo2_1.repository.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private CriteriaRepository criteriaRepository;

//	@Autowired
	private CustomerRepository customerRepository;

	// Constructor injection
	public DemoApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


/*
		// Fetch customers by surname
		List<Customer> smiths = criteriaRepository.findBySurname("Smith");
		System.out.println("Customers with surname 'Smith':");
		smiths.forEach(customer -> System.out.println(customer.getName() + " " + customer.getSurname()));

		// Fetch customers by email
		List<Customer> johnDoe = criteriaRepository.findByEmail("john.doe@example.com");
		System.out.println("Customers with email 'john.doe@example.com':");
		johnDoe.forEach(customer -> System.out.println(customer.getName() + " " + customer.getSurname()));

		// Fetch customers by dynamic fields
		Map<String, Object> fields = new HashMap<>();
		fields.put("name", "John Doe");
		fields.put("surname", "Manager");

		List<Customer> customers = criteriaRepository.findByFields(fields);
		System.out.println("Customers with dynamic fields (name=John and surname=Smith):");
		customers.forEach(customer -> System.out.println(customer.getName() + " " + customer.getSurname()));
*/
		Map<String, Object> fields = new HashMap<>();
		List<String> columns = Arrays.asList("name", "email");
		//fields.clear();
		fields.put("surname", "Manager");

		List<Object[]> projections = criteriaRepository.findWithProjection(columns, fields);
		System.out.println("Customers with surname 'Smith' (only name and email):");
		for (Object[] projection : projections) {
			System.out.println("Name: " + projection[0] + ", Email: " + projection[1]);
		}

		// Create a map for dynamic filters
		fields.clear();
		fields.put("name", "John Doe");
		fields.put("surname", "Manager");
		// Use CustomerSpecifications to filter customers dynamically
		List<Customer> customers = customerRepository.findAll(CustomerSpecification.withDynamicFilter(fields));
		System.out.println("Customers with dynamic fields (name=John and surname=Smith):");
		customers.forEach(customer -> System.out.println(customer.getName() + " " + customer.getSurname()));

	}
}
