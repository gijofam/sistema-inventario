package com.gilmar.sistemainventario.application.command;

// import com.gilmar.sistemainventario.domain.model.Category;

public record UpdateProductCommand(
        String name,
        Long categoryId,
        Long supplierId,
        String description,
        int minimumStock
) {
}
