package com.edu.school.exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final ErrorType errorType;

    public CustomException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public enum ErrorType {
        INVALID_USERNAME,
        INVALID_PASSWORD,
        EMAIL_ALREADY_REGISTERED,
        INVALID_EMAIL,
        RESOURCE_NOT_FOUND,
        FORBIDDEN,
        BAD_REQUEST,
        UNAUTHORIZED,
        CONFLICT,
        INTERNAL_SERVER_ERROR,

        OTHER, 
    }
}