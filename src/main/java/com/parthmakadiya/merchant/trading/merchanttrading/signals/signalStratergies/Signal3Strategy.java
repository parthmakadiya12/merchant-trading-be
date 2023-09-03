package com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalProcessingStrategy;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SignalType(3)
public class Signal3Strategy implements SignalProcessingStrategy {

    private final Algo algo;

    @Autowired
    public Signal3Strategy(Algo algo) {
        this.algo = algo;
    }

    @Override
    public void processSignal() {
        try {
            algo.setAlgoParam(1, 90);
            algo.setAlgoParam(2, 15);
            algo.performCalc();
            algo.submitToMarket();
            algo.doAlgo();
        } catch (Exception ex) {
            throw new SignalProcessingException("Signal 3 failed to process", ex);
        }
    }
}