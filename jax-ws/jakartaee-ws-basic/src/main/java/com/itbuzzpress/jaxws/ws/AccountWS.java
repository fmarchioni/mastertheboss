package com.itbuzzpress.jaxws.ws;


import jakarta.inject.Inject;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;



@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public class AccountWS implements AccountWSItf{
	@Inject
	AccountManager ejb;

 
	public void newAccount(@WebParam(name = "name") String name) {
		ejb.createAccount(name);

	}

 
	public void withdraw(@WebParam(name = "name") String name,
			@WebParam(name = "amount") long amount) throws RuntimeException {
		ejb.withdraw(name, amount);
	}

 
	public void deposit(@WebParam(name = "name") String name,
			@WebParam(name = "amount") long amount) {
		ejb.deposit(name, amount);
	}

	@WebResult(name = "BankAccount")
	public Account findAccountByName(String name) {
		return ejb.findAccount(name);
	}
}
