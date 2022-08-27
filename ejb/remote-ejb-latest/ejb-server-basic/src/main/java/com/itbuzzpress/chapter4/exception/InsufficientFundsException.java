package com.itbuzzpress.chapter4.exception;

import jakarta.ejb.EJBException;

public class InsufficientFundsException extends Exception {
   public InsufficientFundsException(String mess){
	   super(mess);
   }
}
