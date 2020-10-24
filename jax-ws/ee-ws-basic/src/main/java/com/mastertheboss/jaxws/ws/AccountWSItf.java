package com.mastertheboss.jaxws.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService
public interface AccountWSItf {
	@WebMethod
	public String newAccount( String name);

	@WebMethod
	public String withdraw(String name, long amount) throws RuntimeException;

	@WebMethod
	public String deposit(String name, long amount);

	@WebMethod
	public Account findAccountByName(String name);
}
