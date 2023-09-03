package com.parthmakadiya.merchant.trading.merchanttrading.services;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalNotFoundException;
import com.parthmakadiya.merchant.trading.merchanttrading.models.SignalRequest;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalProcessingStrategy;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SignalService {

    public void handleSignal(SignalRequest signalRequest) {
      //TODO: implement this method
    }
}
