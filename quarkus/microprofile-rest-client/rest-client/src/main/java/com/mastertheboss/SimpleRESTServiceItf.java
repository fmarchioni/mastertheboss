package com.mastertheboss;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// Quarkus notation
//@RegisterRestClient(configKey="hello-service")

@RegisterRestClient
@Path("/api")
public interface SimpleRESTServiceItf {
    @GET
    @Path("/text")
    public String getHello();

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleProperty getPropertyJSON();


}