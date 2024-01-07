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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Math1Test {

    @Test
    public void testAddition() {
        int result = Calculator.add(3, 7);
        Assertions.assertEquals(10, result, "3 + 7 should equal 10");
    }

    @Test
    public void testSubtraction() {
        int result = Calculator.subtract(10, 4);
        Assertions.assertEquals(6, result, "10 - 4 should equal 6");
    }
}