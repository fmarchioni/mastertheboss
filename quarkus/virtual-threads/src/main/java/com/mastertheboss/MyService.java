package com.mastertheboss;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import io.quarkus.panache.common.Sort;
import io.smallrye.common.annotation.RunOnVirtualThread;


@Path("tickets")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@RunOnVirtualThread
public class MyService {

    @GET

    public List<Ticket> get() {
        System.out.printf("Called on %s", Thread.currentThread());       
        return Ticket.listAll(Sort.by("name"));
    }

   
    @GET
    @Path("{id}")
    public Ticket getSingle(@PathParam(value = "1") Long id) {
        System.out.printf("Called on %s \n", Thread.currentThread());
        Ticket entity = Ticket.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Ticket with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Ticket ticket) {
        if (ticket.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        ticket.persist();
        return Response.ok(ticket).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Ticket update(@PathParam(value = "1") Long id, Ticket ticket) {
        if (ticket.name == null) {
            throw new WebApplicationException("Ticket Name was not set on request.", 422);
        }

        Ticket entity = Ticket.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Ticket with id of " + id + " does not exist.", 404);
        }

        entity.name = ticket.name;

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam(value = "1") Long id) {
        Ticket entity = Ticket.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Ticket with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }

    }
     
}