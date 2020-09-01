package com.sample;

 

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ProcessMain {

	public static void main(String[] args) throws Exception {
		
	
		KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();

	    KieSession ksession2 = kc.newKieSession("sample-process-rules");
	  
		Account account = new Account();
        account.setMoney(0);
		ksession2.insert(account);
		ksession2.startProcess("com.sample.HelloWorld");
		ksession2.fireAllRules();

	}

}