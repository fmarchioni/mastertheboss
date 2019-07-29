package com.mastertheboss.healthdemo.rest;

import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;


import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.Socket;

@Liveness
@ApplicationScoped
public class DatabaseHealthCheck implements HealthCheck {



    @Override
    public HealthCheckResponse call() {

        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Database connection health check");
        String hostName = (System.getenv("POSTGRESQL_SERVICE_HOST") != null) ?  System.getenv("POSTGRESQL_SERVICE_HOST") : "localhost";
        Integer port = (System.getenv("POSTGRESQL_SERVICE_PORT") != null) ? Integer.parseInt(System.getenv("POSTGRESQL_SERVICE_PORT")) : 5432;

        try {
            pingServer(hostName, port);
            responseBuilder.up();
        } catch (Exception e) {

            responseBuilder.down()
                    .withData("error", e.getMessage());
        }

        return responseBuilder.build();
    }

    private void pingServer(String dbhost, int port) throws IOException
    {
        Socket socket = new Socket(dbhost, port);
        socket.close();

    }


}