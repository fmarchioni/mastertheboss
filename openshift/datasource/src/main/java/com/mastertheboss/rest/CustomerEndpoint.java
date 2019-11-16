package com.mastertheboss.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mastertheboss.model.Customer;

@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerEndpoint {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction tx;

    @GET
    public List<Customer> getAllCustomers() {
        List<Customer> tasks = new ArrayList<>();
        try {
            tx.begin();
            tasks = em.createNamedQuery("findAllCustomer").getResultList();
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
        return tasks;
    }
}
