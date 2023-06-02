package com.sample;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

/**
 * The simplest Hello service.
 */
@WebService(name = "HelloService", serviceName = "HelloService")
public interface HelloService {

    @WebMethod
    String sayHello(String text);

}
