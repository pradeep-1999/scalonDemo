package com.example.scalongDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketTickerDto {

    private String symbol;

    private Double lastPrice;

    private Double change24h;

    private Double volume24h;
}