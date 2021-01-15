package com.example.repository;

import com.example.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@Stateless
public class CustomerRepository {
    @PersistenceContext
    EntityManager em;

    @PersistenceUnit
    EntityManagerFactory emf;


    public void createCustomer(Customer customer) {
        em.persist(customer);
        System.out.println("Created Customer");

    }

    public void updateCustomer( Customer customer ) {

        Customer customerToUpdate = findCustomerById(customer.getId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setAddress(customer.getAddress());
    }

    public void deleteCustomer(Long customerId) {

        Customer c = findCustomerById(customerId);
        em.remove(c);
    }

    public Customer findCustomerById(Long id) {

        Customer customer = em.find(Customer.class, id);

        if (customer == null) {
            throw new WebApplicationException("Customer with id of " + id + " does not exist.", 404);
        }
        return customer;
    }

    public List<Customer> findAllCustomers() {
        Query query = em.createQuery("FROM Customer");
        List<Customer> customerList = query.getResultList();
        return customerList;
    }

    public Customer findCustomerByName(String name) {
        Query query = em
                .createQuery("SELECT c FROM Customer c WHERE c.name = :name");
        query.setParameter("name", name);
        Customer customer = (Customer) query.getSingleResult();
        return customer;
    }


}
