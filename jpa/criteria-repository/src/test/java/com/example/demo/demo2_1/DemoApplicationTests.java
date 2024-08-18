package com.example.demo.demo2_1;

import com.example.demo.demo2_1.controller.DemoController;
import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.repository.CustomerRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private DemoController demoController;

	@Autowired
	private CustomerRepository customerRepository;


	@Test
	@Order(1)
	public void testFindAll() {
		// Assert the size of the list returned by findAll
		List<Customer> customers = demoController.findAll();
		assertEquals(5, customers.size());
	}

	//@Test
	public void testFindOne_ExistingId() {
		Long existingId = 1L;
		Optional<Customer> expectedCustomer = customerRepository.findById(existingId);

		// Call the controller method and assert the returned customer
		Optional<Customer> actualCustomer = demoController.findById(existingId);
		assertNotNull(actualCustomer);
		assertEquals(expectedCustomer, actualCustomer.get());
	}



	@Test
	public void testCreate() {
		Customer newCustomer = new Customer("New", "Customer", "new.customer@example.com");

		// Call the controller method and assert the created customer
		ResponseEntity<Customer> responseEntity = demoController.create(newCustomer);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		Customer createdCustomer = responseEntity.getBody();
		assertNotNull(createdCustomer);
		assertEquals(newCustomer.getId(), createdCustomer.getId());

	}

	@Test
	public void testUpdate_ExistingCustomer() {
		Long existingId = 2L;
		Optional<Customer> existingCustomer = customerRepository.findById(existingId);
		existingCustomer.get().setName("Updated Name");

		// Call the controller method and assert the updated customer
		ResponseEntity<Customer> responseEntity = demoController.update(existingCustomer.get());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Customer updatedCustomer = responseEntity.getBody();
		assertNotNull(updatedCustomer);
		assertEquals(existingCustomer.get().getId(), updatedCustomer.getId());
		assertEquals("Updated Name", updatedCustomer.getName());


	}


	@Test
	public void testDelete_ExistingId() {
		Long existingId = 3L;

		// Call the controller method and assert successful deletion
		ResponseEntity<Customer> responseEntity = demoController.delete(existingId); // Assuming delete expects int
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Assert that the customer is removed from the repository
		Optional<Customer> deletedCustomer = customerRepository.findById(existingId);
		assertFalse(deletedCustomer.isPresent());
	}



}