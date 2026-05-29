package com.gilmar.sistemainventario.infrastructure.mapper;

import com.gilmar.sistemainventario.domain.model.Supplier;
import com.gilmar.sistemainventario.infrastructure.entity.SupplierJpaEntity;

public class SupplierPersistenceMapper {
    
    public static Supplier toDomain(SupplierJpaEntity entity) {
        if (entity == null) return null;
        return new com.gilmar.sistemainventario.domain.model.Supplier(
            entity.getId(),
            entity.getName(),
            entity.getContactName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getAddress()
        );
    }

    public static SupplierJpaEntity toEntity(Supplier domain) {
        if (domain == null) return null;
        return new SupplierJpaEntity(
            domain.getId(),
            domain.getName(),
            domain.getContactName(),
            domain.getEmail(),
            domain.getPhone(),
            domain.getAddress()
        );
    }
}
