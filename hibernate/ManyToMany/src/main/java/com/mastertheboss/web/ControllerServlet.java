package com.mastertheboss.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mastertheboss.ejb.ServiceBean;
import com.mastertheboss.model.*;
import java.util.List;


@SuppressWarnings("serial")
@WebServlet("/test")
public class ControllerServlet extends HttpServlet {

    static String PAGE_HEADER = "<html><head><title>helloworld</title></head><body>";

    static String PAGE_FOOTER = "</body></html>";

    @Inject
    ServiceBean helloService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(PAGE_HEADER);
        writer.println(helloService.create());
        Customer c = helloService.findCustomer(1l);
	    List <Address> listAddress = c.getAddresses(); 
	    
	    for (Address address:listAddress) 
	    	writer.println("Address is " + address);
    	 
        writer.println(PAGE_FOOTER);
        writer.close();
    }

}