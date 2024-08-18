package com.example.demo.demo2_1;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.model.CustomerDTO;
import com.example.demo.demo2_1.model.CustomerMapper;
import com.example.demo.demo2_1.repository.CustomerRepository;
import com.example.demo.demo2_1.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class CustomerServiceIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void testFindAll() {
        Customer customer1 = new Customer("John", "Doe", "john.doe@example.com");
        Customer customer2 = new Customer("Jane", "Doe", "jane.doe@example.com");
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        List<CustomerDTO> result = customerService.findAll();

        assertNotNull(result);
        assertEquals(7, result.size());
    }

    @Test
    void testFindById() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);

        CustomerDTO result = customerService.findById(customer.getId());

        assertNotNull(result);
        assertEquals(customer.getName(), result.customerName());
    }

    @Test
    void testFindByIdNotFound() {
        assertThrows(ResponseStatusException.class, () -> {
            customerService.findById(999L);
        });
    }

    @Test
    void testCreate() {
        CustomerDTO customerDTO = new CustomerDTO(0, "John", "Doe", "john.doe@example.com");

        CustomerDTO result = customerService.create(customerDTO);

        assertNotNull(result);
        assertEquals(customerDTO.customerName(), result.customerName());
    }

    @Test
    void testUpdate() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), "John", "Smith", "john.smith@example.com");

        CustomerDTO result = customerService.update(customerDTO, customer.getId());

        assertNotNull(result);
        assertEquals(customerDTO.surname(), result.surname());
    }

    @Test
    void testUpdateNotFound() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com");

        assertThrows(ResponseStatusException.class, () -> {
            customerService.update(customerDTO, 999L);
        });
    }

    @Test
    void testDelete() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);

        customerService.delete(customer.getId());

        assertFalse(customerRepository.findById(customer.getId()).isPresent());
    }
}
