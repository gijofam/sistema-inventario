package com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.NotNull;

public record StockAdjustmentRequest(
        @NotNull Integer delta,
        String reason
) {
}
