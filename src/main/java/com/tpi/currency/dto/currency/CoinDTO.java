package com.tpi.currency.dto.currency;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record CoinDTO(String currency, String name, String rate, @JsonIgnore Float rateFloat) {}
