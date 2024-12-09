package com.example.veterinary_clinic.exceptions;

import lombok.Data;
import java.util.Date;
@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;
}
