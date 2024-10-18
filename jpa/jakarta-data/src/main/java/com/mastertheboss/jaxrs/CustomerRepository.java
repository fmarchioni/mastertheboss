package com.mastertheboss.jaxrs;

import jakarta.data.repository.Delete;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Repository;
 

@Repository
public interface CustomerRepository   {

    @Insert
    void addCustomer(Customer customer);

    @Delete
    void removeCustomer(Customer customer);
 
    @Find
    Customer customer(Long id);

}