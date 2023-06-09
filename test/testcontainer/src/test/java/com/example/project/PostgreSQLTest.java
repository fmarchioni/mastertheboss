/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
public class PostgreSQLTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest");

    private Connection connection;

    @BeforeAll
    public static void setUp() {
        postgresContainer.start();
    }

    @BeforeEach
    public void connectToDatabase() throws Exception {
        String jdbcUrl = postgresContainer.getJdbcUrl();
        String username = postgresContainer.getUsername();
        String password = postgresContainer.getPassword();

        connection = DriverManager.getConnection(jdbcUrl, username, password);
    }

    @AfterEach
    public void closeConnection() throws Exception {
        connection.close();
    }

    @Test
    public void testCurrentDateNotNull() throws Exception {
        // Execute a query to retrieve the current date from the database
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT CURRENT_DATE");

        // Verify that the result set is not null
        assertNotNull(resultSet);

        // Move the cursor to the first row
        resultSet.next();

        // Retrieve the current date value from the result set
        LocalDate currentDate = resultSet.getObject(1, LocalDate.class);
        System.out.println("Date is " + currentDate);
        // Verify that the current date is not null
        assertNotNull(currentDate);
    }
}
