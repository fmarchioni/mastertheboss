package com.example.testrest;

public class ServiceNotAvailableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceNotAvailableException(String msg) {
        super(msg);
    }

    public ServiceNotAvailableException(String msg, Exception ex) {
        super(msg, ex);
    }
}