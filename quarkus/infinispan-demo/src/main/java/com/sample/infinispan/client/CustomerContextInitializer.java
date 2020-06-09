package com.sample.infinispan.client;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;



@AutoProtoSchemaBuilder(includeClasses = { Customer.class}, schemaPackageName = "customer_list")
public interface CustomerContextInitializer extends SerializationContextInitializer {
}