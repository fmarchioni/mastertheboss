package com.sample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TimeResourceTest {

    @Test
    public void testEndpoint() {
        given()
                .when().get("/time?zoneId=CET")
                .then()
                .statusCode(200);


    }

}
