package com.mastertheboss.quarkus.jms;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/jms")
@Produces("application/json")
@Consumes("application/json")
public class JMSEndpoint {

    @Inject
    JMSProducer producer;

    @POST
    public Response sendMessage(String message) {
        producer.sendMessage(message);
        return Response.status(201).build();

    }

}
