package com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryUpdateRequest(
        @NotBlank(message = "El nombre de la categoría es obligatorio") String name,
        String description
) {
}
