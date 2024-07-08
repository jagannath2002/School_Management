package com.edu.school.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.edu.school.DTO.ErrorDetails;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDetails> handleCustomException(CustomException ex, WebRequest request) {
        HttpStatus status;
        switch (ex.getErrorType()) {
            case INVALID_USERNAME:
            
            case UNAUTHORIZED:
                status = HttpStatus.UNAUTHORIZED;
                break;
            case EMAIL_ALREADY_REGISTERED:
            case CONFLICT:
                status = HttpStatus.CONFLICT;
                break;
            case INVALID_EMAIL:
            case INVALID_PASSWORD:
            case BAD_REQUEST:
                status = HttpStatus.BAD_REQUEST;
                break;
            case RESOURCE_NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;
            case FORBIDDEN:
                status = HttpStatus.FORBIDDEN;
                break;
            case INTERNAL_SERVER_ERROR:
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

        ErrorDetails errorDetails = new ErrorDetails(
                status.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}