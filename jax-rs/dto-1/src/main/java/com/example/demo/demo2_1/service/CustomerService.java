package com.example.demo.demo2_1.service;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.model.CustomerDTO;
import com.example.demo.demo2_1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<CustomerDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO findById(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = toEntity(customerDTO);
        repository.save(customer);
        return toDTO(customer);
    }

    public CustomerDTO update(CustomerDTO customerDTO, Long id) {
        Customer existingCustomer = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        existingCustomer.setName(customerDTO.name());
        existingCustomer.setSurname(customerDTO.surname());
        existingCustomer.setEmail(customerDTO.email());

        repository.save(existingCustomer);
        return toDTO(existingCustomer);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getSurname(), customer.getEmail());
    }

    private Customer toEntity(CustomerDTO customerDTO) {
        return new Customer(customerDTO.name(), customerDTO.surname(), customerDTO.email());
    }
}
