package com.mastertheboss.rest;

import com.mastertheboss.model.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerManager {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createCustomer(Customer customer) {
        em.persist(customer);
        System.out.println("Created Customer "+customer);

    }


    public List<Customer> getAllCustomers() {
        List<Customer> tasks = new ArrayList<>();
        try {

            tasks = em.createNamedQuery("findAllCustomer").getResultList();

        } catch (Exception e){
            e.printStackTrace();
        }
        return tasks;
    }
}
