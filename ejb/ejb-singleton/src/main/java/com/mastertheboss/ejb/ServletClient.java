package com.mastertheboss.ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.*;

@WebServlet("/servlet")
public class ServletClient extends HttpServlet {


	@EJB  UserRegistry ejb;
 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
	    ejb.addUser("frank");
        out.println("List of users:");
	    ArrayList<String> list = ejb.getListUsers();       
        for (String s:list) {
        	out.println(s);
        }    	
     
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}