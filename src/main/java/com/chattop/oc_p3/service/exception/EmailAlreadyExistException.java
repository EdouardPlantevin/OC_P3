package com.chattop.oc_p3.service.exception;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String email) {
        super(email + " already exist");
    }
}
