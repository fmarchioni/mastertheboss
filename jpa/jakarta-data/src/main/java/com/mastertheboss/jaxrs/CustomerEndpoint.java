package com.mastertheboss.jaxrs;

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
        return service.findAll();
    }
    
/*  TBD
    @GET
    @Path("all")
    public void findAll() {
          customerRepository.findAllTables();
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
*/
}