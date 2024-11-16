package com.mastertheboss.jakartadata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {

    @Inject CustomerService service;

    @POST
    public Response create(Customer customer) {
        service.createCustomer(customer);
        return Response.status(201).build();
    }

    @GET
    public List<Customer> getAll() {
        return service.findAllCustomers();
    }
    
    @GET
    @Path("{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        return service.findCustomerById(id);
    }

    @GET
    @Path("/name/{name}")
    public List<Customer> getByName(@PathParam("name") String name) {
       return service.findAllCustomersByName(name);
       
    }
    
    @PUT
    @Path("{id}")
    public Response updateCustomerById(@PathParam("id") Long id, Customer updatedCustomer) {
        return service.updateCustomer(id, updatedCustomer);
    }

    @DELETE
    @Path("{id}")
    public Response deleteCustomerById(@PathParam("id") Long id) {
        return service.deleteCustomerById(id);
    }
}