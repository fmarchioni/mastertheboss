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

        // and add the routes (you can specify multiple classes)
        main.addRouteBuilder(MyJMSRouteBuilder.class);
        // now keep the application running until the JVM is terminated (ctrl + c or sigterm)
        main.run(args);


    }

}

