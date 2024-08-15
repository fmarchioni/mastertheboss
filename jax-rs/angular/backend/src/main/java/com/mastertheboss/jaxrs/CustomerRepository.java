package com.mastertheboss.jaxrs;


import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;


@ApplicationScoped
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

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
    public void updateCustomer(long id, Customer customer) {
        Customer c = entityManager.find(Customer.class, id);
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getFirstName());
        c.setEmail(customer.getEmail());
        entityManager.persist(c);

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
