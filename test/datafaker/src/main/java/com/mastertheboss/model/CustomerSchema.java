package com.mastertheboss.model;

import java.util.Locale;
 
import java.util.concurrent.ThreadLocalRandom;

import net.datafaker.Faker;
 
import net.datafaker.transformations.Schema;
import static net.datafaker.transformations.Field.field;

public class CustomerSchema {
      public static Schema<Object, ?> defaultSchema() {
         var faker = new Faker(Locale.getDefault(), ThreadLocalRandom.current());
            return Schema.of(
            field("name", () -> faker.name().firstName()),
            field("surname", () -> faker.name().lastName())
            );
 }
    
}
