package com.example.demo.demo2_1;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.model.CustomerDTO;
import com.example.demo.demo2_1.model.CustomerMapper;
import com.example.demo.demo2_1.repository.CustomerRepository;
import com.example.demo.demo2_1.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerMapper customerMapper;

    @Test
    void testFindAll() {
        Customer customer1 = new Customer("John", "Doe", "john.doe@example.com");
        customer1.setId(1L);
        Customer customer2 = new Customer("Jane", "Doe", "jane.doe@example.com");
        customer2.setId(2L);

        CustomerDTO customerDTO1 = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com");
        CustomerDTO customerDTO2 = new CustomerDTO(2L, "Jane", "Doe", "jane.doe@example.com");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        when(customerMapper.toDTO(customer1)).thenReturn(customerDTO1);
        when(customerMapper.toDTO(customer2)).thenReturn(customerDTO2);

        List<CustomerDTO> result = customerService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(customerDTO1, result.get(0));
        assertEquals(customerDTO2, result.get(1));
    }

    @Test
    void testFindById() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customer.setId(1L);
        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.findById(1L);

        assertNotNull(result);
        assertEquals(customerDTO, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            customerService.findById(1L);
        });
    }

    @Test
    void testCreate() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com");
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");

        when(customerMapper.toEntity(customerDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.create(customerDTO);

        assertNotNull(result);
        assertEquals(customerDTO, result);
    }

    @Test
    void testUpdate() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com");
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customer.setId(1L);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        CustomerDTO result = customerService.update(customerDTO, 1L);

        assertNotNull(result);
        assertEquals(customerDTO, result);
    }

    @Test
    void testUpdateNotFound() {
        CustomerDTO customerDTO = new CustomerDTO(1L, "John", "Doe", "john.doe@example.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            customerService.update(customerDTO, 1L);
        });
    }

    @Test
    void testDelete() {
        doNothing().when(customerRepository).deleteById(1L);

        customerService.delete(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }
}
