 

package com.itbuzzpress.jaxrs.bootstrap;

import java.util.concurrent.TimeUnit;

import jakarta.ws.rs.SeBootstrap;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

 
public class GreeterTestCase {

    private static SeBootstrap.Instance INSTANCE;

    @BeforeAll
    public static void startInstance() throws Exception {
        INSTANCE = SeBootstrap.start(RestActivator.class)
                .toCompletableFuture().get(10, TimeUnit.SECONDS);
        Assertions.assertNotNull(INSTANCE, "Failed to start instance");
    }

    @AfterAll
    public static void stopInstance() throws Exception {
        if (INSTANCE != null) {
            INSTANCE.stop()
                    .toCompletableFuture()
                    .get(10, TimeUnit.SECONDS);
        }
    }

    @Test
    public void greet() {
        try (Client client = ClientBuilder.newClient()) {
            String name="frank";
            final Response response = client.target(INSTANCE.configuration().baseUriBuilder().path("/rest/" + name))
                    .request()
                    .get();
            Assertions.assertEquals(Response.Status.OK, response.getStatusInfo(),
                    () -> String.format("Expected 200 got %d: %s", response.getStatus(), response.readEntity(String.class)));
            Assertions.assertEquals(String.format("Hello %s!", name), response.readEntity(String.class));
        }
    }
}
