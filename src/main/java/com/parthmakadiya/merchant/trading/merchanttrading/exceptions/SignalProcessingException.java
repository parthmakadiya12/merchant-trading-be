package com.parthmakadiya.merchant.trading.merchanttrading.exceptions;

public class SignalProcessingException extends RuntimeException {
    public SignalProcessingException(String message, Exception ex) {
        super(message, ex);
    }

    public SignalProcessingException(String message) {
        super(message);
    }
}
