//usr/bin/env jbang "$0" "$@" ; exit $?

//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.8.2
//DEPS org.hamcrest:hamcrest-library:2.1

import java.io.PrintWriter;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.platform.console.options.CommandLineOptions;
import org.junit.platform.console.tasks.ConsoleTestExecutor;

import java.nio.file.Paths;
import java.util.Collections;

public class JUnit5Example {

    @Test 
    void testGreeting() {
        Greeter hw = new Greeter();
        assertThat(hw.greeting("me"), equalTo("Hello me"));
    }
    
    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(Collections.singletonList(JUnit5Example.class.getName()));
        options.setReportsDir(Paths.get(System.getProperty("user.dir")));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }

    class Greeter {
        String greeting(String name) {
            return "Hello " + (name != null ? name : "");
        }
    }

}
