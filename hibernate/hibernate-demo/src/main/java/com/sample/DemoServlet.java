package com.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.javafaker.Faker;

@SuppressWarnings("serial")
@WebServlet("/HelloWorld")
public class DemoServlet extends HttpServlet {

	static String PAGE_HEADER = "<html><head><title>Hello Hibernate</title></head><body>";

	static String PAGE_FOOTER = "</body></html>";

	@Inject
	EmployeeService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 
        
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);

		service.saveEmployee(randomEmployee());

		writer.println("<h1>Employee List</h1>");
		List<Employee> list = service.findEmployees();
		writer.println("<ul>");
		for (Employee e : list)
			writer.println("<li>" + e.toString() + "</li>");
		writer.println("</ul>");

		writer.println(PAGE_FOOTER);
		writer.close();
	}

	Employee randomEmployee() {
		Faker fake = new Faker();
		Employee employee = new Employee();
		employee.setFirstName(fake.name().firstName());
		employee.setLastName(fake.name().lastName());
		employee.setDept(fake.job().position());
		return employee;
	}
}
