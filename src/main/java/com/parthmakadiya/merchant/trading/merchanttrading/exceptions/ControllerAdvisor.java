package com.parthmakadiya.merchant.trading.merchanttrading.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(SignalProcessingException.class)
    public ResponseEntity<Map<String, Object>> handleSignalProcessingException(SignalProcessingException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", ex.getMessage());
        responseBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.internalServerError().body(responseBody);
    }

    @ExceptionHandler(SignalNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleSignalNotFoundException(SignalNotFoundException ex) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", ex.getMessage());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}