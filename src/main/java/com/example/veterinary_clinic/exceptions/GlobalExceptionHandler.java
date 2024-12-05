package com.example.veterinary_clinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(VeterinaryExistingPhoneNumberException.class)
    public ResponseEntity<ErrorObject> handleExistingEmailException(VeterinaryExistingPhoneNumberException ex) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VeterinaryNotFoundException.class)
    public ResponseEntity<ErrorObject> handleVeterinaryNotFoundException(VeterinaryNotFoundException ex) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VeterinaryFieldsCannotByEmptyException.class)
    public ResponseEntity<ErrorObject> handleVeterinaryFieldsCannotByEmptyException(VeterinaryFieldsCannotByEmptyException ex) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VeterinaryInvalidPhoneNumberException.class)
    public ResponseEntity<ErrorObject> handleVeterinaryInvalidPhoneNumberException(VeterinaryInvalidPhoneNumberException ex) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, ErrorObject>> handleRequestException(MethodArgumentNotValidException ex) {
        Map<String, ErrorObject> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            ErrorObject errorObject = new ErrorObject();
            errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
            errorObject.setMessage(error.getDefaultMessage());
            errorObject.setTimestamp(new Date());
            errors.put(error.getField(), errorObject);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
