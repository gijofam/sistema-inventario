package com.gilmar.sistemainventario.application.query;

import com.gilmar.sistemainventario.domain.exception.ResourceNotFoundException;
import com.gilmar.sistemainventario.domain.model.Category;
import com.gilmar.sistemainventario.infrastructure.entity.CategoryJpaEntity;
import com.gilmar.sistemainventario.infrastructure.repository.CategoryJpaRepository;
import com.gilmar.sistemainventario.infrastructure.mapper.CategoryPersistenceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryQueryService {

    private final CategoryJpaRepository repository;

    public CategoryQueryService(CategoryJpaRepository repository) {
        this.repository = repository;
    }

    public List<Category> listAll() {
        return repository.findAll().stream()
                .map(CategoryPersistenceMapper::toDomain)
                .toList();
    }

    public Category getById(Long id) {
        CategoryJpaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", id));
        return CategoryPersistenceMapper.toDomain(entity);
    }
}
