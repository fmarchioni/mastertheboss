

package com.example.project;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testDivide_NoException() {
        assertDoesNotThrow(() -> calculator.divide(10, 2));
    }

    @Test
    void testSquare_NoException() {
        assertDoesNotThrow(() -> calculator.square(5));
    }

    @Test
    void testSqrt_NoException() {
        assertDoesNotThrow(() -> calculator.sqrt(25.0));
    }

    @Test
    void testParse_NoException() {
        assertDoesNotThrow(() -> calculator.parse("123"));
    }
}

