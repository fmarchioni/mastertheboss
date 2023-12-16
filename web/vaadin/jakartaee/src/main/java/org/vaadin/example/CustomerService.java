package org.vaadin.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerService {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void register(Customer customer) {
        em.persist(customer);
        System.out.println("Customer registered!");
    }
}
