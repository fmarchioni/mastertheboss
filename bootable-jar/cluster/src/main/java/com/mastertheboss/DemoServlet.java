package com.mastertheboss;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns = {"/"})
public class DemoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Integer count = 1;
        HttpSession session = request.getSession(false);
        if (session == null)
        {

            session = request.getSession();
            out.println("Session created: "+session.getId());
            session.setAttribute( "counter", count );
        }

        else
        {
            out.println("Welcome Back!");
            count = (Integer) session.getAttribute( "counter" );
            ++count;
            session.setAttribute( "counter", count );
        }
            out.println("<br>Counter " + count);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}