package com.sample.jberet;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.runtime.context.StepContext;
import jakarta.inject.Inject;

public class HelloWorldBatchlet extends AbstractBatchlet {
	@Inject
	StepContext stepContext;

	@Override
	public String process() {
		String say = stepContext.getProperties().getProperty("say");
		System.out.println(say);
		return "COMPLETED";
	}
}
