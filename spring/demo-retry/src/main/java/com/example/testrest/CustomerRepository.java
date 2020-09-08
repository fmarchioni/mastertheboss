package com.example.testrest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component


public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();



}
