package com.packt.quarkus.chapter5;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("customers")
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

        customerRepository.createCustomer(customer);
        return Response.status(201).build();

    }

    @PUT
    public Response update(Customer customer) {
        customerRepository.updateCustomer(customer);
        return Response.status(204).build();
    }
    @DELETE
    public Response delete(@QueryParam("id") Long customerId) {
        customerRepository.deleteCustomer(customerId);
        return Response.status(204).build();
    }

}
