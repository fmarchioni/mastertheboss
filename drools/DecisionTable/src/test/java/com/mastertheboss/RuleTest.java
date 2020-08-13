package com.mastertheboss;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mastertheboss.model.Customer;

public class RuleTest {
	static final Logger LOG = LoggerFactory.getLogger(RuleTest.class);
	private KieServices kieServices = KieServices.Factory.get();
	private KieSession kSession;

	@Before
	public void setup() {
		Resource dt = ResourceFactory.newClassPathResource("rules/rules.xls", getClass());
		kSession = getKieSession(dt);
	}

	public KieSession getKieSession(Resource dt) {
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem().write(dt);
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
		KieRepository kieRepository = kieServices.getRepository();
		ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
		KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);
		KieSession ksession = kieContainer.newKieSession();

		return ksession;
	}

	@Test
	public void testCustomer1CorrectDiscount() throws Exception {

		Customer customer = new Customer("Frank");
		customer.setAge(4);

		kSession.insert(customer);
		kSession.fireAllRules();

		assertEquals(customer.getDiscount(), 25);
	}

	@Test
	public void testCustomer2CorrectDiscount() throws Exception {
		Customer customer = new Customer("John");
		customer.setAge(1);
		kSession.insert(customer);
		kSession.fireAllRules();

		assertEquals(customer.getDiscount(), 15);
	}

}
