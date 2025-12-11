package com.reactive.webflux.playground.sec03.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Customer [id=%d] does not exist]";

    public CustomerNotFoundException(Integer id) {
        super(String.format(MESSAGE, id));
    }
}
