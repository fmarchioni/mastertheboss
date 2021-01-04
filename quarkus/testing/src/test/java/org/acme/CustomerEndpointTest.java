package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CustomerEndpointTest {

    @Test
    public void testCustomerService() {

        RestAssured.given()
                .when().get("/customer")
                .then()
                .statusCode(200);




    }
}