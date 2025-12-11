package com.reactive.webflux.playground.sec03.exception;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}
