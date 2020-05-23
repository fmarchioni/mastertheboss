package com.packagename.myapp;

import com.vaadin.cdi.annotation.VaadinSessionScoped;

/**
 * Data provider bean scoped for each user session.
 */
@VaadinSessionScoped
public class GreetService {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }
}
