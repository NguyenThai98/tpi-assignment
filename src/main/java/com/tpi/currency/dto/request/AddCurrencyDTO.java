package com.tpi.currency.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @created 27/04/2024 - 16:32
 * @project currency
 * @author: thainguyen
 */
public record AddCurrencyDTO(
    @NotEmpty String currency, @NotEmpty String name, @NotNull Float rate) {}
