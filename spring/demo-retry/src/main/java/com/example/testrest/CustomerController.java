package com.example.testrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repository;
    List<Customer> customerList = new ArrayList<Customer>();

    @PostConstruct
    public void init() {
        customerList.add(new Customer(1, "frank"));
        customerList.add(new Customer(2, "john"));
    }


    @RequestMapping("/list")
    @Retryable(value = {ServiceNotAvailableException.class}, maxAttempts = 2, backoff = @Backoff(delay = 1000))
    public List<Customer> findAll() {


        int random = new Random().nextInt(2);


        if (random == 1) {
            throw new ServiceNotAvailableException("DB Not available! using Spring-retry..");
        }
        System.out.println("Returning data from DB");
        return repository.findAll();
    }

    @Recover
    public List<Customer> getBackendResponseFallback(ServiceNotAvailableException e) {
        System.out.println("Returning cached list.");
        return customerList;
    }


}
