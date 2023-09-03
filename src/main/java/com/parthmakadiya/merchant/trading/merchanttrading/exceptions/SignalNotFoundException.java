package com.parthmakadiya.merchant.trading.merchanttrading.exceptions;

public class SignalNotFoundException extends RuntimeException {
    public SignalNotFoundException(String message) {
        super(message);
    }

    public SignalNotFoundException() {
        super("The signal that you tried to process does not exist");
    }
}
