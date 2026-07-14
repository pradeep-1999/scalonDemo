package com.example.scalongDemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OkxTicker {

    @JsonProperty("instId")
    private String instId;

    @JsonProperty("last")
    private String last;

    @JsonProperty("open24h")
    private String open24h;

    @JsonProperty("vol24h")
    private String vol24h;

    @JsonProperty("volCcy24h")
    private String volCcy24h;
}