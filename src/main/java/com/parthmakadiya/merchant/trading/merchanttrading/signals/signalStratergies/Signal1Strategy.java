package com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalProcessingStrategy;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SignalType(1)
public class Signal1Strategy implements SignalProcessingStrategy {

    private final Algo algo;

    @Autowired
    public Signal1Strategy(Algo algo) {
        this.algo = algo;
    }

    @Override
    public void processSignal() {
        try {
            algo.setUp();
            algo.setAlgoParam(1, 60);
            algo.performCalc();
            algo.submitToMarket();
            algo.doAlgo();
        } catch (Exception e) {
            throw new SignalProcessingException("Signal 1 failed to process", e);
        }
    }
}