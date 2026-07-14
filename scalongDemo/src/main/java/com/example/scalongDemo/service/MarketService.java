package com.example.scalongDemo.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.scalongDemo.client.OkxRestClient;
import com.example.scalongDemo.dto.MarketTickerDto;
import com.example.scalongDemo.dto.OkxTicker;
import com.example.scalongDemo.dto.OkxTickerResponse;

@Service
public class MarketService {

    private final OkxRestClient okxRestClient;

    public MarketService(OkxRestClient okxRestClient) {
        this.okxRestClient = okxRestClient;
    }

    public List<MarketTickerDto> getTop20Pairs() {

        OkxTickerResponse response = okxRestClient.getSpotTickers();

        if (response == null || response.getData() == null) {
            return List.of();
        }

        return response.getData()
                .stream()
                .map(this::convertToMarketTicker)
                .sorted(Comparator.comparing(MarketTickerDto::getVolume24h).reversed())
                .limit(20)
                .toList();
    }

    private MarketTickerDto convertToMarketTicker(OkxTicker ticker) {

        double lastPrice = parseDouble(ticker.getLast());
        double open24h = parseDouble(ticker.getOpen24h());

        double change24h = 0.0;

        if (open24h != 0) {
            change24h = ((lastPrice - open24h) / open24h) * 100;
        }

        return new MarketTickerDto(
                ticker.getInstId(),
                lastPrice,
                Math.round(change24h * 100.0) / 100.0,
                parseDouble(ticker.getVolCcy24h())
        );
    }

    private double parseDouble(String value) {

        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }
}