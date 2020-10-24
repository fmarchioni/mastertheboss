package com.mastertheboss.jaxws.ws;


import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;



@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public class AccountWS implements AccountWSItf{
	@Inject
	AccountManager ejb;

 
	public String newAccount(@WebParam(name = "name") String name) {
		ejb.createAccount(name);
        return "Created Account "+name;
	}

 
	public String withdraw(@WebParam(name = "name") String name,
			@WebParam(name = "amount") long amount) throws RuntimeException {
		ejb.withdraw(name, amount);
		return "withdrawn "+amount;
	}

 
	public String deposit(@WebParam(name = "name") String name,
			@WebParam(name = "amount") long amount) {
		ejb.deposit(name, amount);
		return "deposited "+amount;
	}

	@WebResult(name = "BankAccount")
	public Account findAccountByName(String name) {
		return ejb.findAccount(name);
	}
}
