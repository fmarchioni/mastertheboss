package com.sample.infinispan.client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import org.infinispan.client.hotrod.RemoteCache;

import io.quarkus.infinispan.client.Remote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/infinispan")
@ApplicationScoped
public class InfinispanEndpoint {

    @Inject
    @Remote("local")
    RemoteCache<String, Customer> cache;


    @GET
    @Path("{customerId}")
    @Produces("application/json")
    public Response get(@PathParam("customerId") String id) {
        Customer customer = cache.get(id);
        System.out.println("Got customer " +customer);
        return Response.ok(customer).status(200).build();
    }



    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(Customer customer) {
        cache.put(customer.getId(), customer);
        System.out.println("Created customer " +customer);
        return Response.ok(customer).status(201).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response update(Customer customer) {
        cache.put(customer.getId(), customer);
        System.out.println("Updated customer " +customer);
        return Response.ok(customer).status(202).build();
    }

    @DELETE
    @Path("{customerId}")
    public Response delete(@PathParam("customerId") String id) {
        System.out.println("Deleted id"+id);
        cache.remove(id);
        return Response.ok().status(202).build();
    }
}
