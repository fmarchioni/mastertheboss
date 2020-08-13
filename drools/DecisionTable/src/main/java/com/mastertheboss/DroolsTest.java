package com.mastertheboss;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.mastertheboss.model.Customer;

public class DroolsTest {

	public static final void main(String[] args) {
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");

			Customer customer1 = new Customer("Frank");
			customer1.setAge(4);

			Customer customer2 = new Customer("John");
			customer2.setAge(1);

			FactHandle fact1 = kSession.insert(customer1);
			FactHandle fact2 = kSession.insert(customer2);
			
			kSession.fireAllRules();

			System.out.println("The discount for the Customer " + customer1.getName() + " is " + customer1.getDiscount());
			System.out.println("The discount for the Customer " + customer2.getName() + " is " + customer2.getDiscount());

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}