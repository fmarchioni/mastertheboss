package com.packt.quarkus.chapter5;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.JsonObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class CustomerEndpointTest {

    @Test
    public void testCustomerService() {

        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
                .body("$.size()", is(2));


        JsonObject objOrder = Json.createObjectBuilder()
                .add("item", "bike")
                .add("price", new Long(100))
                .build();


        // Test POST Order for Customer #1
        given()
                .contentType("application/json")
                .body(objOrder.toString())
                .when()
                .post("/orders/1")
                .then()
                .statusCode(201);

        // Create new JSON for Order #1
        objOrder = Json.createObjectBuilder()
                .add("id", new Long(1))
                .add("item", "mountain bike")
                .add("price", new Long(100))
                .build();

        // Test UPDATE Order #1
        given()
                .contentType("application/json")
                .body(objOrder.toString())
                .when()
                .put("/orders")
                .then()
                .statusCode(204);

        // Test GET for Order #1
        given()
                .when().get("/orders?customerId=1")
                .then()
                .statusCode(200)
                .body(containsString("mountain bike"));

        // Test DELETE Order #1
        given()
                .when().delete("/orders/1")
                .then()
                .statusCode(204);


    }
}

