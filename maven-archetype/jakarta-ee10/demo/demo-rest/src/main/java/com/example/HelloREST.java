package com.example;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/hello")
public class HelloREST {

    @GET
    public Response hello(){

        return Response
                .ok( "Hello World")
                .build();
    }

}
