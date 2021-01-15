package org.acme;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
@Path("/customer")
@Produces("application/json")
@Consumes("application/json")
public class CustomerResource {

    @Inject
    EntityManager entityManager;

    @GET
    public Customer[] get() {
        return entityManager.createNamedQuery("Customers.findAll", Customer.class)
                .getResultList().toArray(new Customer[0]);
    }
    @POST
    @Transactional
    public Response create(Customer customer) {
        if (customer.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        entityManager.persist(customer);
        System.out.println("--------------------Creating "+customer);
        return Response.ok(customer).status(201).build();
    }

    @PUT
    @Transactional
    public Customer update(Customer customer) {
        if (customer.getId() == null) {
            throw new WebApplicationException("Customer Id was not set on request.", 422);
        }

        Customer entity = entityManager.find(Customer.class, customer.getId());

        if (entity == null) {
            throw new WebApplicationException("Customer with id of " + customer.getId() + " does not exist.", 404);
        }

        entity.setName(customer.getName());

        return entity;
    }

    @DELETE
    @Transactional
    public Response delete(Customer customer) {
        Customer entity = entityManager.find(Customer.class, customer.getId());
        if (entity == null) {
            throw new WebApplicationException("Customer with id of " + customer.getId() + " does not exist.", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }

    @Inject
    @ConfigProperty(name="message")
    String message;

    @GET
    @Path("/hello")
    public String hello() {
        return message;
    }
}