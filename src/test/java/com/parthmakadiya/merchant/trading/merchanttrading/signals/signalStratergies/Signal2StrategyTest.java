package com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Signal2StrategyTest {
    @Mock
    private Algo algo;

    private Signal2Strategy signal2Strategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        signal2Strategy = new Signal2Strategy(algo);
    }

    @Test
    public void testProcessSignalWithSuccess() {
        doNothing().when(algo).reverse();
        doNothing().when(algo).setAlgoParam(1, 80);
        doNothing().when(algo).submitToMarket();
        doNothing().when(algo).doAlgo();

        signal2Strategy.processSignal();

        verify(algo).reverse();
        verify(algo).setAlgoParam(1, 80);
        verify(algo).submitToMarket();
        verify(algo).doAlgo();
    }

    @Test
    public void testProcessSignalWithAlgoException() {
        doThrow(new RuntimeException("Algo setup failed")).when(algo).submitToMarket();

        assertThrows(SignalProcessingException.class, () -> signal2Strategy.processSignal());
    }
}