package com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class Signal1StrategyTest {

    @Mock
    private Algo algo;

    private Signal1Strategy signal1Strategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        signal1Strategy = new Signal1Strategy(algo);
    }

    @Test
    public void testProcessSignalWithSuccess() {
        doNothing().when(algo).setUp();
        doNothing().when(algo).setAlgoParam(1, 60);
        doNothing().when(algo).performCalc();
        doNothing().when(algo).submitToMarket();
        doNothing().when(algo).doAlgo();

        signal1Strategy.processSignal();

        verify(algo).setUp();
        verify(algo).setAlgoParam(1, 60);
        verify(algo).performCalc();
        verify(algo).submitToMarket();
        verify(algo).doAlgo();
    }

    @Test
    public void testProcessSignalWithAlgoException() {
        doThrow(new RuntimeException("Algo setup failed")).when(algo).setUp();

        assertThrows(SignalProcessingException.class, () -> signal1Strategy.processSignal());
    }
}
