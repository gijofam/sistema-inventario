package com.gilmar.sistemainventario.infrastructure.adapter.output.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.gilmar.sistemainventario.domain.model.Supplier;
import com.gilmar.sistemainventario.domain.port.output.SupplierRepositoryPort;
import com.gilmar.sistemainventario.infrastructure.entity.SupplierJpaEntity;
import com.gilmar.sistemainventario.infrastructure.mapper.SupplierPersistenceMapper;
import com.gilmar.sistemainventario.infrastructure.repository.SupplierJpaRepository;



@Component
public class SupplierPersistenceAdapter implements SupplierRepositoryPort {
  
    private final SupplierJpaRepository repository;

    public SupplierPersistenceAdapter(SupplierJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Supplier save(Supplier supplier) {
        SupplierJpaEntity saved = repository.save(SupplierPersistenceMapper.toEntity(supplier));
        
        return SupplierPersistenceMapper.toDomain(saved);
    }

    public Optional<Supplier> findById(Long id) {
        return repository.findById(id).map(SupplierPersistenceMapper::toDomain);
    }   

    @Override
    public List<Supplier> findAll() {
        return repository.findAll().stream()
                .map(SupplierPersistenceMapper::toDomain)
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

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmailIgnoreCase(email);
    }

}