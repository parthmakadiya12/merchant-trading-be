package com.parthmakadiya.merchant.trading.merchanttrading.configurations;

import com.parthmakadiya.merchant.trading.merchanttrading.library.Algo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgoConfig {

    @Bean
    public Algo algo() {
        return new Algo();
    }
}
