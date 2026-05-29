package com.gilmar.sistemainventario.infrastructure.mapper;

import com.gilmar.sistemainventario.domain.model.Product;
import com.gilmar.sistemainventario.infrastructure.entity.ProductJpaEntity;


public class ProductPersistenceMapper {

    public static Product toDomain(ProductJpaEntity entity) {
        if (entity == null) return null;
        return new Product(
                entity.getId(),
                entity.getName(),
                // entity.getCategory(),
                CategoryPersistenceMapper.toDomain(entity.getCategory()),
                SupplierPersistenceMapper.toDomain(entity.getSupplier()),
                entity.getDescription(),
                entity.getCurrentStock(),
                entity.getMinimumStock()
        );
    }

    // public static ProductJpaEntity toEntity(Product product) {
    //     return new ProductJpaEntity(
    //             product.getId(),
    //             product.getName(),
    //             product.getCategory(),
    //             product.getDescription(),
    //             product.getCurrentStock(),
    //             product.getMinimumStock()
    //     );
    // }

    public static ProductJpaEntity toEntity(Product product) {
        if (product == null) return null;

        return new ProductJpaEntity(
                product.getId(),
                product.getName(),
                // Convertimos el objeto de dominio Categoría a la entidad JPA de categoría
                CategoryPersistenceMapper.toEntity(product.getCategory()),
                SupplierPersistenceMapper.toEntity(product.getSupplier()),
                product.getDescription(),
                product.getCurrentStock(),
                product.getMinimumStock()
        );
    }
}
