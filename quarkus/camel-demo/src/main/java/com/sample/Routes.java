
package com.sample;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.json);

        rest("/persons")
                .get()
                .to("direct:getPersons")

                .post()
                .type(Person.class)
                .to("direct:addPerson");

        from("direct:getPersons")
                .to("jpa://com.sample.Person?resultClass=com.sample.Person&namedQuery=findAll")
                .log("Person List: ");

        from("direct:addPerson")
                .to("jpa://com.sample.Person?usePersist=true");

    }
}
