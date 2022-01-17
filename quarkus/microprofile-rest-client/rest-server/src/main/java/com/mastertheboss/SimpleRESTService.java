package com.mastertheboss;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class SimpleRESTService {
    @GET
    @Path("/text")
    public String getHello () 
    {
        return "hello world!";
    } 
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleProperty getPropertyJSON () 
    {
        SimpleProperty p = new SimpleProperty(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        return p;
    }
   
}