package org.acme;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;

public class CustomProfile implements  QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of("message", "Hi there!");
    }

    @Override
    public String getConfigProfile() {
        return "custom-profile";
    }
}