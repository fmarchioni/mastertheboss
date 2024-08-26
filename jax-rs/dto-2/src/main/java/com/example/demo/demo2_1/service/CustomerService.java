package com.example.demo.demo2_1.service;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.model.CustomerDTO;
import com.example.demo.demo2_1.model.CustomerMapper;
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

    @Autowired
    private CustomerMapper mapper;
    // Converti stream di Customer in CustomerDTO usando method reference
    public List<CustomerDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        // Lambda equivalente:
        // .map(customer -> mapper.toDTO(customer))
    }

    public CustomerDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = mapper.toEntity(customerDTO);
        repository.save(customer);
        return mapper.toDTO(customer);
    }

    public CustomerDTO update(CustomerDTO customerDTO, Long id) {
        Customer existingCustomer = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        existingCustomer.setName(customerDTO.customerName());
        existingCustomer.setSurname(customerDTO.surname());
        existingCustomer.setEmail(customerDTO.email());

        repository.save(existingCustomer);
        return mapper.toDTO(existingCustomer);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
