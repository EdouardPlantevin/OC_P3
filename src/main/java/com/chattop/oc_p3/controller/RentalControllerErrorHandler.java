package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.service.exception.RentalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class RentalControllerErrorHandler {
    @ExceptionHandler(RentalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundREntal(RentalNotFoundException ex) {
        return Map.of("message", ex.getMessage());
    }
}
