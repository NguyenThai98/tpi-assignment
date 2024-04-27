package com.tpi.currency.dto.request;

import jakarta.validation.constraints.NotEmpty;

/**
 * @created 27/04/2024 - 16:32
 * @project currency
 * @author: thainguyen
 */
public record UpdateCurrencyDTO(@NotEmpty String currency, String name, Float rate) {}
