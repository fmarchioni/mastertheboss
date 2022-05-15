package com.sample;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/jsonb")
public class JsonService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/manager")
    public Response createManager() {
    
        
        Response response;
        Employee e = new Manager();

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(e);
        response = Response.ok(jsonString).build();

        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/consultant")
    public Response createConsultant() {

    	Response response;
        Employee e = new Consultant();

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(e);
        response = Response.ok(jsonString).build();

        return response;
    }
}