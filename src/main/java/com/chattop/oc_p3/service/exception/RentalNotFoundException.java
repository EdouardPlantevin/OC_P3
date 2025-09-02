package com.chattop.oc_p3.service.exception;

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(Long id) {
        super("No rental found with id " + id);
    }
}