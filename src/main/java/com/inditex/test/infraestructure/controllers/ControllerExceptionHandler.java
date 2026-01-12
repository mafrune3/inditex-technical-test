package com.inditex.test.infraestructure.controllers;

import com.inditex.test.domain.exceptions.PriceNotFoundException;
import com.inditex.test.infraestructure.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(PriceNotFoundException e) {
        return ResponseEntity.badRequest().body(
                createResponse(HttpStatus.BAD_REQUEST.value(), "PRICE_NOT_FOUND")
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(
                createResponse(HttpStatus.BAD_REQUEST.value(), "INVALID_ARGUMENT_TYPE")
        );
    }

    private ErrorResponse createResponse(int errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }
}
