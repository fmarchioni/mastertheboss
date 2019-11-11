package com.mastertheboss;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class SecuredResourceTest {

    @Test
    public void testHelloEndpoint() {

        given()
                .auth().preemptive().basic("admin", "admin")
                .when()
                .get("/securedAdmin")
                .then()
                .statusCode(HttpStatus.SC_OK);

        given()
                .auth().preemptive().basic("guest", "guest")
                .when()
                .get("/securedGuest")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

}