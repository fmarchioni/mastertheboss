package com.example.demo.demo2_1.controller;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// http://localhost:8080/swagger-ui/index.html

@RestController
public class DemoController {
    @Autowired
    CustomerRepository repository;
    @GetMapping("/list")
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @GetMapping("/id")
    public Optional<Customer> findById(@RequestParam Long id) {
        return repository.findById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        repository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    @PutMapping(value = "/modify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        repository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Customer> delete(@RequestParam Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
