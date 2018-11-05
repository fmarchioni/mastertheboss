 
package com.itbuzzpress.chapter4.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.itbuzzpress.chapter4.ejb.Account;
import com.itbuzzpress.chapter4.ejb.Calculator;
import com.itbuzzpress.chapter4.exception.InsufficientFundsException;

import javax.naming.spi.NamingManager;
public class RemoteEJBClient {

	public static void main(String[] args) throws Exception {
		Calculator calculator = lookupCalculatorEJB();
                 

		Account account = lookupAccountEJB();
		System.out.println("Create Account with 1000$ ");

		account.createAccount(1000l);
		System.out.println("Deposit 250$ ");
		account.deposit(250);

		try {
			System.out.println("Withdraw 500$ ");
			account.withdraw(500);
		} catch (InsufficientFundsException e) {

			e.printStackTrace();
		}
		long money = account.getMoney();
		System.out.println("Money left " + money);
		float totalMoney = calculator.calculateInterest(money);
		System.out.println("Money plus interests " + totalMoney);


	}

	private static Account lookupAccountEJB() throws NamingException {

        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
            jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
      final Context context = new InitialContext(jndiProperties);

/*

		final Hashtable jndiProperties = new Hashtable();
 
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
                jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
                // Pure HTTP
		//jndiProperties.put(Context.PROVIDER_URL,"http://localhost:8080/wildfly-services");

		final Context ctx = new InitialContext(jndiProperties);
 
*/		  

		return (Account) context
				.lookup("ejb:/ejb-server-basic/AccountEJB!com.itbuzzpress.chapter4.ejb.Account?stateful");
	}

	private static Calculator lookupCalculatorEJB() throws NamingException {
	      final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            //use HTTP upgrade, an initial upgrade requests is sent to upgrade to the remoting protocol
            jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8080");
      final Context context = new InitialContext(jndiProperties);

		return (Calculator) context
				.lookup("ejb:/ejb-server-basic/CalculatorEJB!com.itbuzzpress.chapter4.ejb.Calculator");
	}
}
