package com.mastertheboss.jaxrs;


import javax.enterprise.context.ApplicationScoped;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;


@ApplicationScoped
public class CustomerRepository {

    @Inject
    EntityManager entityManager;

    public List<Customer> findAll() {
        return entityManager.createNamedQuery("Customers.findAll", Customer.class)
                .getResultList();
    }

    public Customer findCustomerById(Long id) {

        Customer customer = entityManager.find(Customer.class, id);

        if (customer == null) {
            throw new WebApplicationException("Customer with id of " + id + " does not exist.", 404);
        }
        return customer;
    }
    @Transactional
    public void updateCustomer(Long id, String name, String surname) {

        Customer customerToUpdate = findCustomerById(id);
        customerToUpdate.setName(name);
        customerToUpdate.setSurname(surname);
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


}
