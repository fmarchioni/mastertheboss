package org.acme;

import javax.inject.Inject;

import javax.ws.rs.*;
import java.util.List;

@Path("/customer")
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {

    @Inject CustomerService service;

    @GET
    public List<Customer> list() {
        return service.list();
    }

    @POST
    public List<Customer> add(Customer customer) {
        service.add(customer);
        return list();
    }

    @PUT
    public List<Customer> put(Customer customer) {
        service.update(customer);
        return list();
    }

    @DELETE
    public List<Customer> delete(Customer customer) {
        service.delete(customer);
        return list();
    }
}