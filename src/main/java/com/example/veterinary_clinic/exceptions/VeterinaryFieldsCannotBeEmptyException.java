package com.example.veterinary_clinic.exceptions;

public class VeterinaryFieldsCannotBeEmptyException extends RuntimeException {
    public VeterinaryFieldsCannotBeEmptyException(String message) {
        super(message);
    }
}
