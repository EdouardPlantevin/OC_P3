package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.service.exception.EmailAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class AccountControllerErrorHandler {
    @ExceptionHandler(EmailAlreadyExist.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleEmailAlreadyExist(EmailAlreadyExist ex) {
        return Map.of("message", ex.getMessage());
    }
}
