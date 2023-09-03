package com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies;

import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class DefaultStrategyTest {
    @Mock
    private Algo algo;

    private DefaultStrategy defaultStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        defaultStrategy = new DefaultStrategy(algo);
    }

    @Test
    public void testProcessSignalWithSuccess() {
        doNothing().when(algo).cancelTrades();

        defaultStrategy.processSignal();

        verify(algo).cancelTrades();
    }
}