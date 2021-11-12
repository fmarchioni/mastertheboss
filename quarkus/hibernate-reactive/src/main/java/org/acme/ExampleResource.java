package org.acme;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.reactive.mutiny.Mutiny;

import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.smallrye.mutiny.Uni;

@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class ExampleResource {
    private static final Logger LOGGER = Logger.getLogger(ExampleResource.class);

    @Inject
    Mutiny.SessionFactory sf;

    @GET
    public Uni<List<Customer>> get() {
        return sf.withTransaction((s,t) -> s
                .createNamedQuery("Customers.findAll", Customer.class)
                .getResultList()
        );
    }

    @GET
    @Path("{id}")
    public Uni<Customer> getSingle(@RestPath Integer id) {
        return sf.withTransaction((s,t) -> s.find(Customer.class, id));
    }

    @POST
    public Uni<Response> create(Customer customer) {
        if (customer == null || customer.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        return sf.withTransaction((s,t) -> s.persist(customer))
                .replaceWith(() -> Response.ok(customer).status(CREATED).build());
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(@RestPath Integer id, Customer customer) {
        if (customer == null || customer.getName() == null) {
            throw new WebApplicationException("Fruit name was not set on request.", 422);
        }

        return sf.withTransaction((s,t) -> s.find(Customer.class, id)
            // If entity exists then update it
            .onItem().ifNotNull().invoke(entity -> entity.setName(customer.getName()))
            .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
            // If entity not found return the appropriate response
            .onItem().ifNull()
            .continueWith(() -> Response.ok().status(NOT_FOUND).build() )
        );
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@RestPath Integer id) {
        return sf.withTransaction((s,t) ->
                s.find(Customer.class, id)
                    // If entity exists then delete it
                    .onItem().ifNotNull()
                        .transformToUni(entity -> s.remove(entity)
                                .replaceWith(() -> Response.ok().status(NO_CONTENT).build()))
                // If entity not found return the appropriate response
                .onItem().ifNull().continueWith(() -> Response.ok().status(NOT_FOUND).build()));
    }

    /**
     * Create a HTTP response from an exception.
     *
     * Response Example:
     *
     * <pre>
     * HTTP/1.1 422 Unprocessable Entity
     * Content-Length: 111
     * Content-Type: application/json
     *
     * {
     *     "code": 422,
     *     "error": "Fruit name was not set on request.",
     *     "exceptionType": "javax.ws.rs.WebApplicationException"
     * }
     * </pre>
     */
    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
/*
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
*/