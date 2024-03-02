package demococumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleSteps {

    private double base;
    private double height;
    private Response response;

    
    @Given("the base is {double} and the height is {double}")
    public void setTriangleProperties(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @When("I call the area endpoint")
    public void callAreaEndpoint() {
        RestAssured.baseURI = "http://localhost:8080/democucumber/rest/";
        response = RestAssured.given()
                .get("/triangle/area?base=" + base + "&height=" + height);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        response.then().assertThat().statusCode(equalTo(expectedStatusCode));
    }

    @Then("the response body should be {double}")
    public void verifyResponseBody(double expectedArea) {
        Double actualArea = Double.parseDouble(response.getBody().asString());
        assertEquals(expectedArea, actualArea);
        
    }
}