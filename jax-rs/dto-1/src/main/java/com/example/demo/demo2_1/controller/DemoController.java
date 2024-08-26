package com.example.demo.demo2_1.controller;

import com.example.demo.demo2_1.model.CustomerDTO;
import com.example.demo.demo2_1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/add")
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.create(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping(value = "/modify")
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

