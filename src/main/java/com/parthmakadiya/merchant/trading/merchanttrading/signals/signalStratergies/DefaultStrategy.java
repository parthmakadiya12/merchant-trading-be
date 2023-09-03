package com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalProcessingStrategy;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SignalType(0)
public class DefaultStrategy implements SignalProcessingStrategy {

    private final Algo algo;

    @Autowired
    public DefaultStrategy(Algo algo) {
        this.algo = algo;
    }

    @Override
    public void processSignal() {
        try {
            algo.cancelTrades();
        } catch (Exception e) {
            throw new SignalProcessingException("Default strategy failed to process", e);
        }
    }
}