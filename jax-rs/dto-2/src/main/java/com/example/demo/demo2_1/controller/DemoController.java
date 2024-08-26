package com.example.demo.demo2_1.controller;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.model.CustomerDTO;
import com.example.demo.demo2_1.repository.CustomerRepository;
import com.example.demo.demo2_1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
// http://localhost:8080/swagger-ui/index.html


@RestController
public class DemoController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public List<CustomerDTO> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<CustomerDTO> findById(@RequestParam Long id) {
        CustomerDTO customerDTO = customerService.findById(id);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.create(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO, @RequestParam Long id) {
        CustomerDTO updatedCustomer = customerService.update(customerDTO, id);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



