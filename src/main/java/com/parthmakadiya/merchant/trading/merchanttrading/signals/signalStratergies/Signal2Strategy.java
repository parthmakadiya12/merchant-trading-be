package com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalProcessingStrategy;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SignalType(2)
public class Signal2Strategy implements SignalProcessingStrategy {

    private final Algo algo;

    @Autowired
    public Signal2Strategy(Algo algo) {
        this.algo = algo;
    }

    @Override
    public void processSignal() {
        try {
            algo.reverse();
            algo.setAlgoParam(1, 80);
            algo.submitToMarket();
            algo.doAlgo();
        } catch (Exception e) {
            throw new SignalProcessingException("Signal 2 failed to process", e);
        }
    }
}