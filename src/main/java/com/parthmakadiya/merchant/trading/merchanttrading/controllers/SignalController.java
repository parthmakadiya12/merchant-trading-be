package com.parthmakadiya.merchant.trading.merchanttrading.controllers;

import com.parthmakadiya.merchant.trading.merchanttrading.models.SignalRequest;
import com.parthmakadiya.merchant.trading.merchanttrading.services.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signals")
public class SignalController {

    private final SignalService signalService;
    @Autowired
    public SignalController(SignalService signalService) {
        this.signalService = signalService;
    }

    @PostMapping
    public ResponseEntity<String> receiveSignal(@RequestBody SignalRequest signalRequest) {
        signalService.handleSignal(signalRequest);
        return ResponseEntity.ok("Signal received and processed");
    }

}
