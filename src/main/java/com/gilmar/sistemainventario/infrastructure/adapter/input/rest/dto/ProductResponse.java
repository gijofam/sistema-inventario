package com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto;



public record ProductResponse(
        Long id,
        String name,
        CategoryResponse category,
        SupplierResponse supplier,
        String description,
        int currentStock,
        int minimumStock
) {
}
