package com.reactive.webflux.playground.sec03.advice;

import com.reactive.webflux.playground.sec03.exception.CustomerNotFoundException;
import com.reactive.webflux.playground.sec03.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleCustomerNotFoundException(CustomerNotFoundException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setType(URI.create("https://example.com/problems/customer-not-found"));
        problem.setTitle("Customer Not Found");
        return problem;
    }

    @ExceptionHandler(InvalidInputException.class)
    public ProblemDetail handleCustomerNotFoundException(InvalidInputException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setType(URI.create("https://example.com/problems/invalid-input"));
        problem.setTitle("invalid input");
        return problem;
    }
}
