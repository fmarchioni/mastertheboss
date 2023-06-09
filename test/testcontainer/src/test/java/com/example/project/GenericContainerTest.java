package com.example.project;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class GenericContainerTest {



    private static final int HTTP_PORT = 80;

    @Container
    private static final GenericContainer<?> container = new GenericContainer<>("httpd:latest")
            .withExposedPorts(HTTP_PORT);

    @Test
    public void testHttpdServer() throws Exception {
        // Get the host and port of the running container
        String host = container.getHost();
        int port = container.getMappedPort(HTTP_PORT);

        // Create the URL for the HTTP request
        URL url = new URL("http://" + host + ":" + port + "/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Send an HTTP GET request
        connection.setRequestMethod("GET");
        connection.connect();

        // Verify the response code is 200 (OK)
        int responseCode = connection.getResponseCode();
        System.out.println("Response code:"+responseCode);
        Assertions.assertEquals(200, responseCode);
    }
}

