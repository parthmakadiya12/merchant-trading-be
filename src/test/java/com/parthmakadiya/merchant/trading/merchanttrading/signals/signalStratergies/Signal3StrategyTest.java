package com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Signal3StrategyTest {
    @Mock
    private Algo algo;

    private Signal3Strategy signal3Strategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        signal3Strategy = new Signal3Strategy(algo);
    }
    @Test
    public void testProcessSignalWithSuccess() {
        doNothing().when(algo).setAlgoParam(1, 90);
        doNothing().when(algo).setAlgoParam(2, 15);
        doNothing().when(algo).performCalc();
        doNothing().when(algo).submitToMarket();
        doNothing().when(algo).doAlgo();

        signal3Strategy.processSignal();

        verify(algo).setAlgoParam(1, 90);
        verify(algo).setAlgoParam(2, 15);
        verify(algo).performCalc();
        verify(algo).submitToMarket();
        verify(algo).doAlgo();
    }

    @Test
    public void testProcessSignalWithAlgoException() {
        doThrow(new RuntimeException("Algo setup failed")).when(algo).doAlgo();

        assertThrows(SignalProcessingException.class, () -> signal3Strategy.processSignal());
    }

}