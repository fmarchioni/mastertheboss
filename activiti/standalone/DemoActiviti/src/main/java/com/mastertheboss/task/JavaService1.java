package com.mastertheboss.task;

import org.activiti.engine.delegate.DelegateExecution;

public class JavaService1 implements org.activiti.engine.delegate.JavaDelegate {

	public void execute(DelegateExecution arg0) {

		if (isComplexTask()) {
			System.out.println("This is a complex task!");
			arg0.setVariable("result", "1");
		}	
		else  {
			System.out.println("This is a simple task!");			
		    arg0.setVariable("result", "0");
	    }

	}

	private boolean isComplexTask() {

		return Math.random() < 0.5;
	}

}