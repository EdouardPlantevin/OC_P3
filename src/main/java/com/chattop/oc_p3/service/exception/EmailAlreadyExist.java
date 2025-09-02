package com.chattop.oc_p3.service.exception;

public class EmailAlreadyExist extends RuntimeException {
    public EmailAlreadyExist(String email) {
        super(email + " already exist");
    }
}
