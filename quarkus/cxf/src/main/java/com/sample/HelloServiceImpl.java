package com.sample;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * http://localhost:8080/services/HelloService?wsdl
 */
@WebService(serviceName = "HelloService")
public class HelloServiceImpl implements HelloService {

    @WebMethod
    @Override
    public String sayHello(String text) {
        return "Hello " + text + "!";
    }

}
