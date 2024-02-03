package com.example.project;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class UppercaseDisplayNameGenerator implements DisplayNameGenerator {
    @Override
    public String generateDisplayNameForClass(Class<?> testClass) {
        return testClass.getSimpleName().toUpperCase();
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
        return nestedClass.getSimpleName().toUpperCase();
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return testMethod.getName().toUpperCase();
    }


}