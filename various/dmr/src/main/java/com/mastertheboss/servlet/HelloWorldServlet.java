package com.mastertheboss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;
@WebServlet(name = "hello", urlPatterns = { "/jndi" })
public class HelloWorldServlet extends HttpServlet {
	 


	public HelloWorldServlet() {
		super();

	}
	
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            PrintWriter out = response.getWriter();
	            ModelNode op = new ModelNode();
	            op.get("operation").set("jndi-view");
	            ModelNode address = op.get("address");
	            address.add("subsystem", "naming");
	            op.get("recursive").set(true);
	            op.get("operations").set(true);
	            ModelControllerClient client = ModelControllerClient.Factory.create(InetAddress.getByName("localhost"), 9999);
	            ModelNode returnVal = client.execute(op);
	            out.println(returnVal.get("result").toString());
	            out.close();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
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
