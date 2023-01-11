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


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculatorTest {
	private final Calculator calculator = new Calculator();

	@Test
	void testDivideByZero() {
		assertThrows(IllegalArgumentException.class,
				() -> calculator.divide(1, 0));
	}

	@Test
	void testSquareWithNegativeNumber() {
		assertThrows(IllegalArgumentException.class,
				() -> calculator.square(-1));
	}

	@Test
	void testBothMethods() {
		assertAll("exception tests",
				() -> assertThrows(IllegalArgumentException.class,
						() -> calculator.divide(1, 0)),
				() -> assertThrows(IllegalArgumentException.class,
						() -> calculator.square(-1))
		);
	}
}
