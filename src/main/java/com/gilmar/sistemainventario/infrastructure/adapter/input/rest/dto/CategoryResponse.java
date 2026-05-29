package com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto;

public record CategoryResponse(
        Long id,
        String name,
        String description
) {
}
