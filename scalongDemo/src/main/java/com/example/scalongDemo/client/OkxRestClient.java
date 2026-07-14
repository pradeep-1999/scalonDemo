package com.example.scalongDemo.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.scalongDemo.dto.OkxTickerResponse;


@Service
public class OkxRestClient {

    private final WebClient webClient;

    public OkxRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public OkxTickerResponse getSpotTickers() {

        return webClient.get()
                .uri("/api/v5/market/tickers?instType=SPOT")
                .retrieve()
                .bodyToMono(OkxTickerResponse.class)
                .block();
    }
}