package com.itbuzzpress.chapter4.ejb;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

@Stateless
@Remote(Calculator.class)
public class CalculatorEJB implements Calculator {
   
	float interest=5;
 
	@Override
	public float calculateInterest(long money) {
	 
	    return money * (1+ (interest/100));
	    
	   
   }
	
	 
	
}
