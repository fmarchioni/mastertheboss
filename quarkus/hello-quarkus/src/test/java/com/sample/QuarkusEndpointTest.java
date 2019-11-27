package com.sample;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class QuarkusEndpointTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/persons")
          .then()
             .statusCode(200);

    }

}