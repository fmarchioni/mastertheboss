package org.acme;
import io.quarkus.test.junit.callback.QuarkusTestAfterEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestBeforeEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestMethodContext;

public class TestCallBackExample implements QuarkusTestBeforeEachCallback, QuarkusTestAfterEachCallback {

    public void beforeEach(QuarkusTestMethodContext context) {
        System.out.println("Executing " + context.getTestMethod());
    }
    public void afterEach(QuarkusTestMethodContext context) {
        System.out.println("Executed " + context.getTestMethod());
    }
}
