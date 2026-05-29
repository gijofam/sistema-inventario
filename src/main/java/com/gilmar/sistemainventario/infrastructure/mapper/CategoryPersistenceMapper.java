package com.gilmar.sistemainventario.infrastructure.mapper;

import com.gilmar.sistemainventario.domain.model.Category;
import com.gilmar.sistemainventario.infrastructure.entity.CategoryJpaEntity;

public class CategoryPersistenceMapper {

    public static Category toDomain(CategoryJpaEntity entity) {
        if (entity == null) return null;
        return new Category(
            entity.getId(), 
            entity.getName(), 
            entity.getDescription());
    }

    public static CategoryJpaEntity toEntity(Category domain) {
        if (domain == null) return null;
        return new CategoryJpaEntity(
            domain.getId(),
            domain.getName(), 
            domain.getDescription());
    }
}
