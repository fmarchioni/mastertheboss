package com.mastertheboss.jaxrs;

import java.util.UUID;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Repository;
 

@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long>  {

    @Insert
    void addCustomer(Customer customer);

    @Delete
    void removeCustomer(Customer customer);
 
    @Find
    Customer customer(Long id);

}