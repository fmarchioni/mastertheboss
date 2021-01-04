package org.acme;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeCustomerResourceIT extends CustomerEndpointTest {

    // Execute the same tests but in native mode.
}