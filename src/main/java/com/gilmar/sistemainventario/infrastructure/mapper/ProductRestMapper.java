package com.gilmar.sistemainventario.infrastructure.mapper;

import com.gilmar.sistemainventario.application.command.AdjustStockCommand;
import com.gilmar.sistemainventario.application.command.CreateProductCommand;
import com.gilmar.sistemainventario.application.command.UpdateProductCommand;
import com.gilmar.sistemainventario.domain.model.Product;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.ProductCreateRequest;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.ProductResponse;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.ProductUpdateRequest;
import com.gilmar.sistemainventario.infrastructure.adapter.input.rest.dto.StockAdjustmentRequest;

public class ProductRestMapper {

    // public static Product toDomain(ProductCreateRequest request) {
    //     return new Product(null, request.name(), request.category(), request.description(), request.currentStock(), request.minimumStock());
    // }

    // public static Product toDomain(ProductUpdateRequest request) {
    //     return new Product(null, request.name(), request.category(), request.description(), 0, request.minimumStock());
    // }

    public static CreateProductCommand toCreateCommand(ProductCreateRequest request) {
        if (request == null) return null;
        return new CreateProductCommand(
                request.name(),
                request.categoryId(),
                request.supplierId(),
                request.description(),
                request.currentStock(),
                request.minimumStock()
        );
    }

    public static UpdateProductCommand toUpdateCommand(ProductUpdateRequest request) {
        if (request == null) return null;
        return new UpdateProductCommand(
                request.name(),
                request.categoryId(),
                request.supplierId(),
                request.description(),
                request.minimumStock()
        );
    }

    public static AdjustStockCommand toAdjustCommand(StockAdjustmentRequest request) {
        return new AdjustStockCommand(request.delta(), request.reason());
    }

    public static ProductResponse toResponse(Product product) {
        if (product == null) return null;

        return new ProductResponse(
                product.getId(),
                product.getName(),
                CategoryRestMapper.toResponse(product.getCategory()),
                SupplierRestMapper.toResponse(product.getSupplier()),
                product.getDescription(),
                product.getCurrentStock(),
                product.getMinimumStock()
        );
    }
}
