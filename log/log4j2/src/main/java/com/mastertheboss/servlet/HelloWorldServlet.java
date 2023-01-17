package com.mastertheboss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "hello", urlPatterns = { "/hello" })
public class HelloWorldServlet extends HttpServlet {


	Logger logger = LogManager.getLogger(HelloWorldServlet.class);

	public HelloWorldServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<h1>Hello World Servlet on WildFly</h1>");
		logger.warn("Hello world Log4j2 on WildFly");
		logger.warn("Request URI {} - Session Id {}.", request.getRequestURI(), request.getSession().getId());

		writer.close();
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
