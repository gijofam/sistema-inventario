package com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto;

// import com.gilmar.sistemainventario.domain.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequest(
        @NotBlank String name,
        // @NotNull Category category,
        @NotNull(message = "El ID de la categoría es obligatorio") Long categoryId,
        @NotNull(message = "El ID del proveedor es obligatorio") Long supplierId,
        @NotBlank String description,
        @Min(0) int minimumStock
) {
}
