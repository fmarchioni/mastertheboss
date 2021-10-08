package com.mastertheboss.microprofile.health;
 

import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.net.Socket;

@Readiness
@ApplicationScoped
public class DatabaseHealthCheck implements HealthCheck {

	@Inject
	@ConfigProperty(name = "POSTGRESQL_SERVICE_HOST", defaultValue = "localhost")
	private String host;
	
	@Inject
	@ConfigProperty(name = "POSTGRESQL_SERVICE_PORT", defaultValue = "5432")
	private int port;

    @Override
    public HealthCheckResponse call() {

        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Database connection health check");
     
        try {
            pingServer(host, port);
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