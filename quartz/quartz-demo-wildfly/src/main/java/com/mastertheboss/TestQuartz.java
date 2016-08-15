package com.mastertheboss;

import java.io.IOException;
import java.util.Date;
  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;

import org.quartz.*;
import org.quartz.impl.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;
@WebServlet("/TestQuartz")
public class TestQuartz extends HttpServlet {
private static final long serialVersionUID = 1L;
        
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
          
        try {
            // step 1
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        sched.start();
        
          
        Date runTime = evenMinuteDate(new Date());
        // Trigger the job to run on the next round minute
        Trigger trigger = newTrigger()
           .withIdentity("trigger1", "group1")
           .startAt(runTime)
           .build();
        // Define job instance
        JobDetail job1 = newJob(HelloJob.class)
           .withIdentity("job1", "group1")
           .build();
                              
        // Schedule the job with the trigger 
        sched.scheduleJob(job1, trigger); 

       // Set response content type
        response.setContentType("text/html");

       // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>Quartz Job Scheduled in a minute</h1>");
  }
        
        
         catch ( Exception de) {
            throw new IOException(de.getMessage());
        }
    }
  
}
