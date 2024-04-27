package com.tpi.currency.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Currency(
    String code,
    String symbol,
    String rate,
    String description,
    @JsonProperty("rate_float") double rateFloat) {}
