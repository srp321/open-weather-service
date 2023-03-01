package com.assignment.openweatherservice.exception;

import com.assignment.openweatherservice.model.GlobalErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WeatherDataException.class)
    public ResponseEntity<?> weatherDataException(WeatherDataException ex) {

        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildErrorResponse(String body, HttpStatus status) {
        GlobalErrorResponse globalErrorResponse = new GlobalErrorResponse(status.value(), body);
        return ResponseEntity.status(status).body(globalErrorResponse);
    }
}
