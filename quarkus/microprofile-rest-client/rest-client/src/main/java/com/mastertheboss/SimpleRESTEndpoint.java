package com.mastertheboss;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/proxy")
@ApplicationScoped
public class SimpleRESTEndpoint {

    @Inject
    @RestClient
    SimpleRESTServiceItf service;

    @GET
    @Path("/text")
    public String getHello() {
        return service.getHello();
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleProperty getPropertyJSON(){
        return service.getPropertyJSON();
    }

    

}