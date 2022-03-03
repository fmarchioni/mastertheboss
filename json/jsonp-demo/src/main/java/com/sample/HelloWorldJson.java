
package com.sample;

import java.io.IOException;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/json")
public class HelloWorldJson extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        final JsonGenerator generator = Json.createGenerator(response.getWriter());
       
        generator.writeStartObject();
        generator.write("Hello", "World");
        generator.write("Balance", new Double(1000));
        generator.write("IsMember", new Boolean(true));
        generator.writeEnd();
        generator.close();
    }
}