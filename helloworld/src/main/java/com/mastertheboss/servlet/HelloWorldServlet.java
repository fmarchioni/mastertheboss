package com.mastertheboss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "hello", urlPatterns = { "/hello" })
public class HelloWorldServlet extends HttpServlet {
	 


	public HelloWorldServlet() {
		super();

	}
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<h1>Live reload on WildFly</h1>");
		writer.close();
    }
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
