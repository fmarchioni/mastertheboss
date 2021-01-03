package org.acme;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
@Path("/customer")
@Produces("application/json")
@Consumes("application/json")
public class ExampleResource {

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
        System.out.println("Creating "+customer);
        entityManager.persist(customer);
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
}