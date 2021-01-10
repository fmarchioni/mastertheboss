package org.acme;

import org.bson.types.ObjectId;

import javax.inject.Inject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customer")
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {


    @GET
    public List<Customer> list() {
        return Customer.listAll();
    }

    @POST
    public Response create(Customer customer) {
        customer.persist();
        return Response.status(201).build();
    }

    @PUT
    public void update(Customer customer) {
        System.out.println("------ customer "+customer);
        customer.update();
    }

    @DELETE
    public void delete(Customer c) {
        Customer customer = Customer.findById(c.id);
        customer.delete();
    }
}