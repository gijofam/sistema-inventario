package com.gilmar.sistemainventario.infrastructure.adapter.output.persistence;

import com.gilmar.sistemainventario.domain.model.Category;
import com.gilmar.sistemainventario.domain.port.output.CategoryRepositoryPort;
import com.gilmar.sistemainventario.infrastructure.entity.CategoryJpaEntity;
import com.gilmar.sistemainventario.infrastructure.repository.CategoryJpaRepository;
import com.gilmar.sistemainventario.infrastructure.mapper.CategoryPersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryPersistenceAdapter implements CategoryRepositoryPort {

    private final CategoryJpaRepository jpaRepository;

    public CategoryPersistenceAdapter(CategoryJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Category save(Category category) {
        CategoryJpaEntity entity = CategoryPersistenceMapper.toEntity(category);
        CategoryJpaEntity savedEntity = jpaRepository.save(entity);
        return CategoryPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return jpaRepository.findById(id)
                .map(CategoryPersistenceMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpaRepository.findAll().stream()
                .map(CategoryPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByNameIgnoreCase(name);
    }
}
