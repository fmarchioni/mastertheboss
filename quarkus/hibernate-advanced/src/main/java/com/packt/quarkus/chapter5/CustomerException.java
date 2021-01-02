package com.packt.quarkus.chapter5;

public class CustomerException extends RuntimeException{

    public CustomerException() {
        super();
    }
    public CustomerException(String exc) {
        super(exc);
    }

}
