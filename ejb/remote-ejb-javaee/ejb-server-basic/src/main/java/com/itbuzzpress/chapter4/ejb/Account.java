package com.itbuzzpress.chapter4.ejb;

import com.itbuzzpress.chapter4.exception.InsufficientFundsException;

 
public interface Account {


	public void deposit(long amount);
	public void withdraw(long amount) throws InsufficientFundsException;
	
	public long getMoney();
	public void createAccount(long amount);
}