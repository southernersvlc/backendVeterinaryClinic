package com.example.veterinary_clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VeterinaryFieldsCannotByEmptyException extends RuntimeException {
    public VeterinaryFieldsCannotByEmptyException(String message) {
        super(message);
    }
}
