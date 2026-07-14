package com.example.scalongDemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.scalongDemo.dto.MarketTickerDto;
import com.example.scalongDemo.service.MarketService;

@RestController
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/top20")
    public List<MarketTickerDto> getTop20() {
        return marketService.getTop20Pairs();
    }
}