package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SimplePostRequestSecret {

    public static void main(String[] args) {
        String tokenUrl = "http://localhost:8080/realms/ApplicationRealm/protocol/openid-connect/token";
        String apiUrl = "http://localhost:8180/api/admin";

        String grantType = "password";
        String clientId = "my-client";
        String clientSecret = "mysecret"; // Keycloak client secret
        String username = "user1";  // only user1 authorized
        String password = "123456";

        try {
            // Prepare form data
            Map<Object, Object> data = new HashMap<>();
            data.put("grant_type", grantType);
            data.put("client_id", clientId);
            data.put("client_secret", clientSecret); // Add client secret to the form data
            data.put("username", username);
            data.put("password", password);

            String form = data.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            // Create HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create HttpRequest for POST to get the token
            HttpRequest tokenRequest = HttpRequest.newBuilder()
                    .uri(URI.create(tokenUrl))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(form))
                    .build();

            // Send the POST request
            HttpResponse<String> tokenResponse = client.send(tokenRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("POST Response Code :: " + tokenResponse.statusCode());

            if (tokenResponse.statusCode() == 200) { // success
                String responseBody = tokenResponse.body();
                System.out.println("Response Body: " + responseBody);

                // Parse JSON response
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                JsonNode accessTokenNode = rootNode.path("access_token");
                String token = accessTokenNode.asText();

                if (!accessTokenNode.isMissingNode()) {
                    System.out.println("Access Token: " + token);

                    // Create HttpRequest for GET to access the secured endpoint
                    HttpRequest apiRequest = HttpRequest.newBuilder()
                            .uri(URI.create(apiUrl))
                            .header("Authorization", "Bearer " + token)
                            .GET()
                            .build();

                    // Send the GET request
                    HttpResponse<String> apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
                    System.out.println("GET Response Code :: " + apiResponse.statusCode());

                    if (apiResponse.statusCode() == 200) { // success
                        System.out.println("GET Response: " + apiResponse.body());
                    } else {
                        System.out.println("GET request not worked");
                        System.out.println("Error Response: " + apiResponse.body());
                    }
                } else {
                    System.out.println("Response: " + responseBody);
                }
            } else {
                System.out.println("POST request not worked");
                System.out.println("Error Response: " + tokenResponse.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}