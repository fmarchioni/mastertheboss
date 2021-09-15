package com.mastertheboss.jaxrs;


import javax.enterprise.context.ApplicationScoped;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;


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


}
