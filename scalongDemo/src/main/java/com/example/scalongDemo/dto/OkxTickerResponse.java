package com.example.scalongDemo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OkxTickerResponse {

    private String code;
    private String msg;
    private List<OkxTicker> data;
}