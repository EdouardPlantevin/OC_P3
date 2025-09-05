package com.chattop.oc_p3.service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found with id :" + id);
    }
}