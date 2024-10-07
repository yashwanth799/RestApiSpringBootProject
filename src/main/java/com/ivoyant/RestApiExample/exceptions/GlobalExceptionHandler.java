package com.ivoyant.RestApiExample.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionHandler {

    private CustomExceptionObject customExceptionObject;

    GlobalExceptionHandler(CustomExceptionObject customExceptionObject){
        this.customExceptionObject = customExceptionObject;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        customExceptionObject.setStatus(HttpStatus.NOT_FOUND.value());
        customExceptionObject.setMessage(ex.getMessage());
        customExceptionObject.setTimestamp(new Timestamp(System.currentTimeMillis()));
        customExceptionObject.setDetails("URI="+request.getRequestURI());
        customExceptionObject.setException(ex.getClass().getSimpleName());
        customExceptionObject.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customExceptionObject);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, HttpServletRequest request) {
        customExceptionObject.setStatus(HttpStatus.NOT_FOUND.value());
        customExceptionObject.setMessage(ex.getMessage());
        customExceptionObject.setTimestamp(new Timestamp(System.currentTimeMillis()));
        customExceptionObject.setDetails("URI="+request.getRequestURI());
        customExceptionObject.setException(ex.getClass().getSimpleName());
        customExceptionObject.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customExceptionObject);
    }
}
