package com.itbuzzpress.exception;

import javax.ejb.EJBException;

public class InsufficientFundsException extends Exception {
   public InsufficientFundsException(String mess){
	   super(mess);
   }
}
