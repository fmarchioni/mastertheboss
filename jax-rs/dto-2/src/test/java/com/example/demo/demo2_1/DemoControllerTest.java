package com.example.demo.demo2_1;

import com.example.demo.demo2_1.model.Customer;
import com.example.demo.demo2_1.repository.CustomerRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DemoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        customerRepository.deleteAll();
    }

    @Test
    void testFindAll() {
        Customer customer1 = new Customer("Frank", "Doe", "frank.doe@example.com");
        Customer customer2 = new Customer("Jane", "Doe", "jane.doe@example.com");
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Response response = given()
                .when()
                .get("/list")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();

        // Print the response body for debugging
        System.out.println("Response Body: " + response.asString());

        // Perform assertions
        response
                .then()
                .body("$", hasSize(2))
                .body("customerName", containsInAnyOrder("Frank", "Jane"))
                .body("surname", containsInAnyOrder("Doe", "Doe"));
    }

    @Test
    void testFindById() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);

        Response response = given()
                .queryParam("id", customer.getId())
                .when()
                .get("/id")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();

        // Print the response body for debugging
        System.out.println("Response Body: " + response.asString());

        // Perform assertions
        response
                .then()
                .body("customerName", equalTo("John"))
                .body("surname", equalTo("Doe"))
                .body("email", equalTo("john.doe@example.com"));
    }

    @Test
    void testCreate() {
        Map<String, Object> customerDTO = new HashMap<>();
        customerDTO.put("customerName", "John");
        customerDTO.put("surname", "Doe");
        customerDTO.put("email", "john.doe@example.com");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(customerDTO)
                .when()
                .post("/add")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response();

        // Print the response body for debugging
        System.out.println("Response Body: " + response.asString());

        // Perform assertions
        response
                .then()
                .body("customerName", equalTo("John"))
                .body("surname", equalTo("Doe"))
                .body("email", equalTo("john.doe@example.com"));
    }

    @Test
    void testUpdate() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);

        Map<String, Object> customerDTO = new HashMap<>();
        customerDTO.put("customerName", "John");
        customerDTO.put("surname", "Smith");
        customerDTO.put("email", "john.smith@example.com");

        Response response = given()
                .queryParam("id", customer.getId())
                .contentType(ContentType.JSON)
                .body(customerDTO)
                .when()
                .put("/modify")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response();

        // Print the response body for debugging
        System.out.println("Response Body: " + response.asString());

        // Perform assertions
        response
                .then()
                .body("customerName", equalTo("John"))
                .body("surname", equalTo("Smith"))
                .body("email", equalTo("john.smith@example.com"));
    }

    @Test
    void testDelete() {
        Customer customer = new Customer("John", "Doe", "john.doe@example.com");
        customerRepository.save(customer);

        given()
                .queryParam("id", customer.getId())
                .when()
                .delete("/delete")
                .then()
                .statusCode(HttpStatus.OK.value());

        assertFalse(customerRepository.findById(customer.getId()).isPresent());
    }
}
