package com.mastertheboss.jaxws.test;

import static org.junit.Assert.*;

import java.net.URL;


import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.mastertheboss.jaxws.ws.Account;
import com.mastertheboss.jaxws.ws.AccountWSItf;


public class TestWS {

	@Test
	public void test() {
		 
		String name ="John Smith";
		try {
			URL url = new URL("http://localhost:8080/ee-ws-basic/AccountWS?wsdl");
			QName qname = new QName("http://ws.jaxws.mastertheboss.com/",
					"AccountWSService");

			Service service = Service.create(url, qname);
			AccountWSItf ws = service.getPort(AccountWSItf.class);
			ws.newAccount(name);
			System.out.println("Created account name " +name);
			ws.deposit(name, 1000);
			System.out.println("Deposit $ 1000 ");
			ws.withdraw(name, 500);
			System.out.println("Withdraw $ 500 ");
			Account account = ws.findAccountByName(name);
			assertNotNull(account);

			long money = account.getAmount();
			assertEquals(500l, money);
			System.out.println("Account balance is " +account.getAmount());


		} catch (Exception e) {
			System.out.println("Exception: "+ e);
		}
	}
	@Test
	public void testApacheCXF() {
		String name ="Adam Smith";
		System.out.println("TEST Using Apache CXF native API");

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(AccountWSItf.class);
		factory.setAddress("http://localhost:8080/ee-ws-basic/AccountWS?wsdl");
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());

		AccountWSItf ws = (AccountWSItf) factory.create(); 
		ws.newAccount(name);
		System.out.println("Created account name " +name);
		ws.deposit(name, 1000);
		System.out.println("Deposit $ 1000 ");
		ws.withdraw(name, 500);
		System.out.println("Withdraw $ 500 ");
		Account account = ws.findAccountByName(name);
		assertNotNull(account);
		long money = account.getAmount();
		assertEquals(500l, money);
		System.out.println("Account amount is " +account.getAmount());
 

	}
}
