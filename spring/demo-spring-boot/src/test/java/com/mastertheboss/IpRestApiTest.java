/**
 *  Copyright 2005-2015 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package com.mastertheboss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {IpRestApiTest.class, App.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
@EnableAutoConfiguration
public class IpRestApiTest extends Assert {

    RestTemplate rest = new TestRestTemplate();

    @Autowired
    EmbeddedWebApplicationContext tomcat;

    int port;
    String baseUri;

    @Before
    public void before() {
        port = tomcat.getEmbeddedServletContainer().getPort();
        baseUri = "http://localhost:" + port;
    }

    @Test
    public void shouldExposeIpApi() throws InterruptedException {
        String ip = rest.getForObject(baseUri + "/ip", String.class);
        assertNotNull(ip);

        String ip2 = rest.getForObject(baseUri + "/ip", String.class);
        assertNotNull(ip2);

        // should not be same as there is a counter in the response
        assertNotSame(ip, ip2);
    }

}
