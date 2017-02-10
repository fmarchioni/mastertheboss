package com.mastertheboss;

import javax.inject.*;

public class SimpleService {
    @Inject
    private Hello greeter;

    public void greet()
    {
        greeter.greet();
    }
}
