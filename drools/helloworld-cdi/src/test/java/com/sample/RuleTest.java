/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package com.sample;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RuleTest {

    @Test
    public void testGo() {

        Weld w = new Weld();
        WeldContainer wc = w.initialize();

        com.sample.CDIExample bean = wc.instance().select(com.sample.CDIExample.class).get();


        Server s1 = new Server("rhel7",2,2048,4096);

        boolean isValid = bean.go(s1);
        assertTrue(isValid);

        w.shutdown();

    }
}