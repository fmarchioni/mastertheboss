package com.mastertheboss;

public class CustomerException extends RuntimeException{

    public CustomerException() {
        super();
    }
    public CustomerException(String exc) {
        super(exc);
    }

}
