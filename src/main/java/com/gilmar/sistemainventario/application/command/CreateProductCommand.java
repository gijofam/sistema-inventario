package com.gilmar.sistemainventario.application.command;

// import com.gilmar.sistemainventario.domain.model.ProductCategory;

public record CreateProductCommand(
        String name,
        Long categoryId,
        Long supplierId,
        String description,
        int currentStock,
        int minimumStock
) {
}
