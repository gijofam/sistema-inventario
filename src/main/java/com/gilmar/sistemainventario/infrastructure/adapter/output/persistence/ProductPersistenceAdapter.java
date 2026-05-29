package com.gilmar.sistemainventario.infrastructure.adapter.output.persistence;

import com.gilmar.sistemainventario.domain.model.Product;
import com.gilmar.sistemainventario.domain.port.output.ProductRepositoryPort;
import com.gilmar.sistemainventario.infrastructure.entity.ProductJpaEntity;
import com.gilmar.sistemainventario.infrastructure.mapper.ProductPersistenceMapper;
import com.gilmar.sistemainventario.infrastructure.repository.ProductJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository repository;

    public ProductPersistenceAdapter(ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity saved = repository.save(ProductPersistenceMapper.toEntity(product));
        return ProductPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(ProductPersistenceMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
                // .map(ProductPersistenceMapper::toDomain)
                .map(entity -> ProductPersistenceMapper.toDomain(entity))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByNameIgnoreCase(name);
    }
}
