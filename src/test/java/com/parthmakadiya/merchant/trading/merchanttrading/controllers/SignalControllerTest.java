package com.parthmakadiya.merchant.trading.merchanttrading.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalNotFoundException;
import com.parthmakadiya.merchant.trading.merchanttrading.exceptions.SignalProcessingException;
import com.parthmakadiya.merchant.trading.merchanttrading.models.SignalRequest;
import com.parthmakadiya.merchant.trading.merchanttrading.services.SignalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@WebMvcTest(SignalController.class)
@AutoConfigureMockMvc
public class SignalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SignalService signalService;

    @Test
    public void testReceiveSignal() throws Exception {
        SignalRequest signalRequest = new SignalRequest();
        signalRequest.setSignal(1);

        doNothing().when(signalService).handleSignal(any(SignalRequest.class));

        String jsonRequest = objectMapper.writeValueAsString(signalRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/signals")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assert (content.equals("Signal received and processed"));
    }

    @Test
    public void shouldReturnExceptionWhenServiceThrowsSignalNotFoundException() throws Exception {
        SignalRequest signalRequest = new SignalRequest();
        signalRequest.setSignal(1);

        doThrow(new SignalNotFoundException()).when(signalService).handleSignal(any(SignalRequest.class));

        String jsonRequest = objectMapper.writeValueAsString(signalRequest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/signals")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"The signal that you tried to process does not exist\"," +
                                "\"status\":404}"));
    }


    @Test
    public void shouldReturnExceptionWhenServiceThrowsSignalProcessingException() throws Exception {
        SignalRequest signalRequest = new SignalRequest();
        signalRequest.setSignal(1);

        doThrow(new SignalProcessingException("Signal Cannot be processed.")).when(signalService).handleSignal(any(SignalRequest.class));

        String jsonRequest = objectMapper.writeValueAsString(signalRequest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/signals")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Signal Cannot be processed.\",\"status\":500}"));
    }
}