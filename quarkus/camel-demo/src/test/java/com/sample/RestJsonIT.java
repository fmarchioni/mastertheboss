
package com.sample;

import io.quarkus.test.junit.QuarkusIntegrationTest;

/**
 * Native mode tests. In the native mode, the same tests will be executed as in the JVM mode because this class extends
 * {@link RestJsonTest}.
 */
@QuarkusIntegrationTest
public class RestJsonIT extends RestJsonTest {
}
