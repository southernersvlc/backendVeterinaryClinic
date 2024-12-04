package com.example.veterinary_clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GuardianPhoneNumberAlreadyExistsException extends RuntimeException {
    public GuardianPhoneNumberAlreadyExistsException(String message) {
        super(message);
    }
}
