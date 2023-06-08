/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

class PrivateTest {

	@Test
	void testPrivateMethod() throws Exception {
		// Create an instance of MyClass
		MyClass myClass = new MyClass();

		// Obtain the private method using reflection
		Method privateMethod = MyClass.class.getDeclaredMethod("privateMethod", String.class);
		privateMethod.setAccessible(true);

		// Invoke the private method on the instance
		String result = (String)
				privateMethod.invoke(myClass, "john");

		// Assert the result
		assertEquals("Hello john", result);
	}
}
