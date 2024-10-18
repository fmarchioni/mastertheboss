package com.mastertheboss.jaxrs;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

 
@ApplicationScoped
public class CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    CustomerRepository repository;

    @Transactional
    public void createCustomer(Customer customer) {

        repository.addCustomer(customer);

    }

    public List<Customer> findAll() {
        return entityManager.createNamedQuery("Customers.findAll", Customer.class)
                .getResultList();
    }
/* 
   

    public void findAllTables() {
        try (Connection conn = entityManager.unwrap(Connection.class)) {
            Script.process(conn, "dump.sql", null, "TABLES *");
        } catch (Exception e) {
            // Handle exception
        }
    }


    public Customer findCustomerById(Long id) {

        Customer customer = entityManager.find(Customer.class, id);

        if (customer == null) {
            throw new WebApplicationException("Customer with id of " + id + " does not exist.", 404);
        }
        return customer;
    }
    @Transactional
    public void updateCustomer(Customer customer) {

        Customer customerToUpdate = findCustomerById(customer.getId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setSurname(customer.getSurname());
    }
    @Transactional
    public void createCustomer(Customer customer) {

        entityManager.persist(customer);

    }
    @Transactional
    public void deleteCustomer(Long customerId) {

        Customer c = findCustomerById(customerId);
        entityManager.remove(c);
    }

*/
}
