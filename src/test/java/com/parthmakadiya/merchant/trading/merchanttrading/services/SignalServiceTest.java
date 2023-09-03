package com.parthmakadiya.merchant.trading.merchanttrading.services;

import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalNotFoundException;
import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.models.SignalRequest;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.SignalProcessingStrategy;
import com.parthmakadiya.merchant.trading.merchanttrading.signals.signalStratergies.Signal1Strategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class SignalServiceTest {

    private SignalService signalService;

    @Mock
    private Signal1Strategy strategy1;

    List<SignalProcessingStrategy> strategies = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(strategy1).processSignal();

        strategies.add(strategy1);
        signalService= new SignalService(strategies);
    }

    @Test
    public void testHandleSignalWithFoundStrategy() {
        SignalRequest signalRequest = new SignalRequest();
        signalRequest.setSignal(1);

        signalService = new SignalService(strategies);

        signalService.handleSignal(signalRequest);
        Mockito.verify(strategy1, Mockito.times(1)).processSignal();
    }

    @Test
    public void testHandleSignalWithNotFoundStrategy() {
        SignalRequest signalRequest = new SignalRequest();
        signalRequest.setSignal(3); // Non existent signal

        signalService = new SignalService(strategies);

        assertThrows(SignalNotFoundException.class, () -> signalService.handleSignal(signalRequest));
    }

    @Test
    public void shouldThrowErrorIfWeGetExceptionFromSignal() {
        doThrow(new SignalProcessingException("Error processing signal 1.")).when(strategy1).processSignal();

        SignalRequest signalRequest = new SignalRequest();
        signalRequest.setSignal(1);

        signalService = new SignalService(strategies);

        assertThrows(SignalProcessingException.class, () -> signalService.handleSignal(signalRequest));
    }
}