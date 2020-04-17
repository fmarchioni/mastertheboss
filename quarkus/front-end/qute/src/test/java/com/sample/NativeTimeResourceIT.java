package com.sample;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeTimeResourceIT extends TimeResourceTest {

    // Execute the same tests but in native mode.
}