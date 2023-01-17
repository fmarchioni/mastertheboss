package com.mastertheboss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  // Create a logger
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {

    // Log a debug message
    logger.debug("This is a debug message");

    // Log an info message
    logger.info("This is an info message");

    // Log a warning message
    logger.warn("This is a warning message");

    // Log a warning message
    logger.error("This is a error message");

    int errorCode=15;
    String errorMSG="ERR0015";
    // Log an error message
    logger.error("Hit the error-code {} and error-message {}", errorCode, errorMSG);

  }
}
