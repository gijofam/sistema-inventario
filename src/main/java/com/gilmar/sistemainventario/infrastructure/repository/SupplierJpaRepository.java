package com.gilmar.sistemainventario.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gilmar.sistemainventario.infrastructure.entity.SupplierJpaEntity;

public interface SupplierJpaRepository extends JpaRepository<SupplierJpaEntity, Long> {
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByNameIgnoreCase(String name);    
}
