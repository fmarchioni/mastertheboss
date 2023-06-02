package com.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkiverse.cxf.test.QuarkusCxfClientTestUtil;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class HelloServiceTest {

    @Test
    public void hello() {
        final HelloService client = QuarkusCxfClientTestUtil.getClient(HelloService.class, "/services/HelloService");
        Assertions.assertEquals("Hello World!", client.sayHello("World"));
    }

}