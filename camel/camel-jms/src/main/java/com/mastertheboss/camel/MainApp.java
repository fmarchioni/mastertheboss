package com.mastertheboss.camel;

import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        // use Camels Main class
        Main main = new Main();
        // lets use a configuration class (you can specify multiple classes)
        // (properties are automatic loaded from application.properties)
        main.addConfigurationClass(CustomConfiguration.class);
        // and add the routes (you can specify multiple classes)
        main.addRouteBuilder(MyRouteBuilder.class);
        // now keep the application running until the JVM is terminated (ctrl + c or sigterm)
        main.run(args);


    }

}

