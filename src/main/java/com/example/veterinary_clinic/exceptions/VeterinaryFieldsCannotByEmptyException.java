package com.example.veterinary_clinic.exceptions;

public class VeterinaryFieldsCannotByEmptyException extends RuntimeException {
    public VeterinaryFieldsCannotByEmptyException(String message) {
        super(message);
    }
}
