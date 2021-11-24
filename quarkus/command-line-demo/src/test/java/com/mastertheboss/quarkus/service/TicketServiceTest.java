package com.mastertheboss.quarkus.service;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@QuarkusMainTest
public class TicketServiceTest {

    @Test
    @Launch({ "John", "AB1"})
    public void testLaunchCommand(LaunchResult result) {
    	assertTrue(result.getOutput().indexOf("Ticket created") > 0 );
    }

    @Test
    @Launch(value = {}, exitCode = 1)
    public void testLaunchCommandFailed() {
    }

   
}