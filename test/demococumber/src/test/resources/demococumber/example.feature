Feature: Calculate Triangle Area

Scenario: Calculate area of a triangle
    Given the base is 10.0 and the height is 5.0
    When I call the area endpoint
    Then the response status code should be 200
    And the response body should be 25.0
