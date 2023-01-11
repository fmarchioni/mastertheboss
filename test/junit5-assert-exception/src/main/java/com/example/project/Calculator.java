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

public class Calculator {
	public int divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException("Divisor cannot be zero");
		}
		return dividend / divisor;
	}

	public int square(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("Number cannot be negative");
		}
		return number * number;
	}
}
