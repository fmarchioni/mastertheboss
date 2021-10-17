package com.mastertheboss;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MyServiceTest {

    @Test
    public void testListAllTickets() {
        given()
                .when().get("/tickets")
                .then()
                .statusCode(200)
                .body(
                        containsString("Phantom of the Opera"),
                        containsString("Chorus Line"),
                        containsString("Mamma mia")
                );

    }

}
