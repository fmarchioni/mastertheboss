package com.mastertheboss.rest;

import com.mastertheboss.model.Customer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerEndpoint {

@Inject CustomerManager manager;

    @POST
    public void createCustomer(Customer customer) {
        manager.createCustomer(customer);
    }

    @GET
    public List<Customer> getAllCustomers() {
        return manager.getAllCustomers();
    }
}
