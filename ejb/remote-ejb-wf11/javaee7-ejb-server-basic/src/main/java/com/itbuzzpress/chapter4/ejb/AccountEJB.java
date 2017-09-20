package com.itbuzzpress.chapter4.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import com.itbuzzpress.chapter4.exception.InsufficientFundsException;
 

@Stateful
@Remote(Account.class)
public class AccountEJB implements Account {

    long money;
 
    
	@Override
	public long getMoney() {
		return money;

	}

	public void createAccount(long amount)  
	{
		this.money= amount;
		 
	}

	@Override
	public void deposit(long amount)  
	{
		 
			this.money+= amount;
			 
		System.out.println("Money deposit. total is "+money);
	}
 
	 
	@Override
	public void withdraw(long amount) throws InsufficientFundsException {
		
		long newAmount = money - amount;
		if (newAmount < 0) {
			throw new InsufficientFundsException("Unsufficient funds for account! ");
		}
		
		money = newAmount;	 
		System.out.println("Money withdrawal. total is "+money);

	}
}
