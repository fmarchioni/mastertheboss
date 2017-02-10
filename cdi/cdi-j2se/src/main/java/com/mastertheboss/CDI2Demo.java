package com.mastertheboss;

import javax.enterprise.inject.spi.*;
import javax.enterprise.inject.se.*;

public class CDI2Demo {
	public static void main(String... args) {
		SeContainerInitializer containerInit = SeContainerInitializer.newInstance();
		SeContainer container = containerInit.initialize();

		// Fire synchronous event that triggers the code in App class.
		container.getBeanManager().fireEvent(new SimpleEvent());

		container.close();
	}
}
