package com.sample;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// Import the SLF4J API
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@WebServlet("/HelloWorld")
public class HelloWorldServlet extends HttpServlet {
  private static final Logger logger = LoggerFactory.getLogger(HelloWorldServlet.class);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

				// Log a debug message
				logger.debug("This is a debug message");

				// Log an info message
				logger.info("This is an info message");

				// Log a warning message
				logger.warn("This is a warning message");

				// Log an error message
				logger.error("This is an error message");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("Hello World");
        writer.close();
    }

}
