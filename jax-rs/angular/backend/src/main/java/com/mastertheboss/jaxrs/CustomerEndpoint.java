package com.mastertheboss.jaxrs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("customer")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {

    @Inject CustomerRepository customerRepository;

    @GET
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @POST
    public Response create(Customer customer) {
        System.out.println(customer);
        customerRepository.createCustomer(customer);
        return Response.status(201).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        Customer customer = customerRepository.findCustomerById(id);
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {

        customerRepository.updateCustomer(id, customer);
        return Response.ok(customer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        System.out.println("deleting "+id);
        customerRepository.deleteCustomer(id);
        return Response.noContent().build();
    }

}