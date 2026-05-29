package com.gilmar.sistemainventario.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gilmar.sistemainventario.infrastructure.entity.CategoryJpaEntity;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long>   {

    boolean existsByNameIgnoreCase(String name);
    
}
