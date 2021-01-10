package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class CustomerEndpointTest {

    @Test
    public void testCustomerService() {

        given()
                .when().get("/customer")
                .then()
                .statusCode(200);



    }
}