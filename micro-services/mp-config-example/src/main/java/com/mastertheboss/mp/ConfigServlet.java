package com.mastertheboss.mp;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@WebServlet(name = "config", urlPatterns = { "/config" })

public class ConfigServlet extends HttpServlet {

    @Inject
    Config config;

    @Inject
    @ConfigProperty(name = "property1", defaultValue="hello")
    String prop1;

    @Inject
    @ConfigProperty(name = "property2")
    String prop2;

    public ConfigServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        response.getWriter().append("Got property1 with: ").append(prop1);
        response.getWriter().append("Got property2 with: ").append(prop2);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
