package com.example.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Math2Test {

    @Test
    public void testMultiplication() {
        int result = Calculator.multiply(5, 6);
        Assertions.assertEquals(30, result, "5 * 6 should equal 30");
    }

    @Test
    public void testDivision() {
        double result = Calculator.divide(20, 5);
        Assertions.assertEquals(4.0, result, "20 / 5 should equal 4.0");
    }
}

