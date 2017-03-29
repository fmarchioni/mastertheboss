package infinispan;

 

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

 
import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 

/**
 * <p>
 * A simple asynchronous servlet taking advantage of features added in 3.0.
 * </p>
 * 
 * <p>
 * The servlet is registered and mapped to /AsynchronousServlet using the {@link WebServlet} annotation. The
 * {@link LongRunningService} is injected by CDI.
 * </p>
 * 
 * <p>
 * It shows how to detach the execution of a long-running task from the request processing thread, so the thread is free
 * to serve other client requests. The long-running tasks are executed using a dedicated thread pool and create the
 * client response asynchronously using the {@link AsyncContext}.
 * </p>
 * 
 * <p>
 * A long-running task in this context does not refer to a computation intensive task executed on the same machine but
 * could for example be contacting a third-party service that has limited resources or only allows for a limited number
 * of concurrent connections. Moving the calls to this service into a separate and smaller sized thread pool ensures
 * that less threads will be busy interacting with the long-running service and that more requests can be served that do
 * not depend on this service.
 * </p>
 * 
 * @author Christian Sadilek <csadilek@redhat.com>
 */
@SuppressWarnings("serial")
@WebServlet(value = "/test" )
public class TestServlet extends HttpServlet {

 
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse response) {
    // Here the request is put in asynchronous mode
	  response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out=null;
	try {
		out = response.getWriter();
		
		  
	        FootballManager manager = new FootballManager();
	 
            manager.printTeams(out);
	     
	             
 
	        
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      out.println("<h1>Called Servlet</h1>");
  }
}
