package com.gilmar.sistemainventario.application.command;

import com.gilmar.sistemainventario.domain.exception.BusinessRuleException;
import com.gilmar.sistemainventario.domain.exception.ResourceNotFoundException;
import com.gilmar.sistemainventario.domain.model.Category;
import com.gilmar.sistemainventario.domain.model.Product;
import com.gilmar.sistemainventario.domain.model.Supplier;
import com.gilmar.sistemainventario.domain.port.input.AdjustStockUseCase;
import com.gilmar.sistemainventario.domain.port.input.CreateProductUseCase;
import com.gilmar.sistemainventario.domain.port.input.DeleteProductUseCase;
import com.gilmar.sistemainventario.domain.port.input.UpdateProductUseCase;
import com.gilmar.sistemainventario.domain.port.output.ProductRepositoryPort;
import com.gilmar.sistemainventario.infrastructure.entity.CategoryJpaEntity;
import com.gilmar.sistemainventario.infrastructure.entity.SupplierJpaEntity;
import com.gilmar.sistemainventario.infrastructure.mapper.CategoryPersistenceMapper;
import com.gilmar.sistemainventario.infrastructure.mapper.SupplierPersistenceMapper;
import com.gilmar.sistemainventario.infrastructure.repository.CategoryJpaRepository;
import com.gilmar.sistemainventario.infrastructure.repository.SupplierJpaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ProductCommandService implements CreateProductUseCase, UpdateProductUseCase, AdjustStockUseCase, DeleteProductUseCase {

    private final CategoryJpaRepository categoryJpaRepository;
    private final ProductRepositoryPort repositoryPort;
    private final SupplierJpaRepository supplierJpaRepository;

    // public ProductCommandService(ProductRepositoryPort repositoryPort) {
    //     this.repositoryPort = repositoryPort;
    // }

    // 2. ACTUALIZAR EL CONSTRUCTOR CON AMBOS REPOSITORIOS
    public ProductCommandService(
        ProductRepositoryPort repositoryPort, 
        CategoryJpaRepository categoryJpaRepository,
        SupplierJpaRepository supplierJpaRepository) {
        this.repositoryPort = repositoryPort;
        this.categoryJpaRepository = categoryJpaRepository;
        this.supplierJpaRepository = supplierJpaRepository;
    }

    @Override
    public Product create(CreateProductCommand command) {
        if (repositoryPort.existsByName(command.name())) {
            throw new BusinessRuleException("Ya existe un producto con ese nombre.");
        }

        // Category category = new Category(command.categoryId(), null, null);


        //agregamos esto para corregir los null del nombre y description al momento de mandar un post
        // 1. Buscamos la entidad real en la base de datos (con su nombre y descripción reales)
        CategoryJpaEntity categoryEntity = categoryJpaRepository.findById(command.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", command.categoryId()));
        // 2. La transformamos a objeto de dominio completo usando tu mapper
        Category category = CategoryPersistenceMapper.toDomain(categoryEntity);


        SupplierJpaEntity supplierEntity = supplierJpaRepository.findById(command.supplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", command.supplierId()));
        Supplier supplier = SupplierPersistenceMapper.toDomain(supplierEntity);
                
        Product product = new Product(
                null,
                command.name(),
                category,
                supplier,
                command.description(),
                command.currentStock(),
                command.minimumStock()
        );

        return repositoryPort.save(product);
    }

    @Override
    public Product update(Long id, UpdateProductCommand command) {
        Product existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));

        // 1. Buscamos la entidad real en la base de datos (con su nombre y descripción reales)
        CategoryJpaEntity categoryEntity = categoryJpaRepository.findById(command.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", command.categoryId()));

        // 2. La transformamos a objeto de dominio completo usando tu mapper
        Category category = CategoryPersistenceMapper.toDomain(categoryEntity);

        SupplierJpaEntity supplierEntity = supplierJpaRepository.findById(command.supplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor", command.supplierId()));
        Supplier supplier = SupplierPersistenceMapper.toDomain(supplierEntity);

        existing.updateDetails(command.name(), category, supplier, command.description(), command.minimumStock());
        return repositoryPort.save(existing);
    }

    @Override
    public Product adjustStock(Long id, AdjustStockCommand command) {
        Product existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));

        if (command.delta() == 0) {
            throw new BusinessRuleException("La variación de stock debe ser distinta de cero.");
        }

        existing.adjustStock(command.delta());
        return repositoryPort.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (repositoryPort.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Producto", id);
        }
        repositoryPort.deleteById(id);
    }
}
