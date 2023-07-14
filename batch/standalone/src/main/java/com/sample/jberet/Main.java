package com.sample.jberet;

import java.util.concurrent.TimeUnit;

import org.jberet.runtime.JobExecutionImpl;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.BatchRuntime;

public class Main {
    private static final String simpleJob = "simplebatchlet.xml";
    private static final JobOperator jobOperator = BatchRuntime.getJobOperator();
    public static void main(String[] args) {
        try {
            final long jobExecutionId = jobOperator.start(simpleJob, null);            
            final JobExecutionImpl jobExecution = (JobExecutionImpl) jobOperator.getJobExecution(jobExecutionId);
            jobExecution.awaitTermination(5, TimeUnit.MINUTES);
        } catch (Exception ex) {
            System.out.println("Error submitting Job! " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
