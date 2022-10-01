package com.itbuzzpress.jaxrs.bootstrap;

import java.util.Collections;
import java.util.Set;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class HelloWorld extends Application {

    /**
     * Returns the sole, singleton resource.
     */
    @Override
    public Set<Class<?>> getClasses() {
        return Collections.singleton(HelloWorld.class);
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response greet(@PathParam("name") final String name) {
        return Response.ok("Hello " +name).build();
    }

}
