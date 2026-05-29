package com.gilmar.sistemainventario.infrastructure.repository;

import com.gilmar.sistemainventario.infrastructure.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {

    boolean existsByNameIgnoreCase(String name);
}
